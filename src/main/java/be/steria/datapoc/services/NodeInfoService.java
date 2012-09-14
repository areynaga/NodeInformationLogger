package be.steria.datapoc.services;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

@SuppressWarnings("restriction")
@WebService
public interface NodeInfoService {
	public List<String> getNodeList();
	public String getAddress(@WebParam(name="nodeId")String nodeId);
	public String getCentralNodeId();
	public void reloadFile() throws Exception;
	public void configure(@WebParam(name="centralNode")int centralNode, 
			@WebParam(name="nodeAddresses")List<String> nodeAddresses) throws Exception;
}
