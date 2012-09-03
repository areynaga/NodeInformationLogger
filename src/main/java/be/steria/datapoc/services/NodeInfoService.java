package be.steria.datapoc.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

@SuppressWarnings("restriction")
@WebService
public interface NodeInfoService {
	public List<String> getNodeList();
	public String getAddress(@WebParam(name="nodeId")String nodeId);
	public String getCentralNodeId();
	public void reloadInfo() throws FileNotFoundException, IOException;
}
