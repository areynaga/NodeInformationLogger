package be.steria.datapoc.services;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.jws.WebParam;
import javax.jws.WebService;


@SuppressWarnings("restriction")
@WebService
public class NodeInfoServiceImpl implements NodeInfoService {

	private String configFolder;
	
	private Properties properties;
	
	public NodeInfoServiceImpl() {
		Properties configProperties = new Properties();
		
		properties = new Properties();
		try {
			
			configProperties.load(this.getClass().getClassLoader()
					.getResourceAsStream("config.properties"));
			configFolder = configProperties.getProperty("config.folder");
			properties.load(new FileInputStream(configFolder + "nodeConfig.properties"));
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public List<String> getNodeList() {
		List<String> valRet = new ArrayList<String>();
		for (int i=2; i<=Integer.parseInt(properties.getProperty("nodes.size")); i++) 
			valRet.add(String.valueOf(i));
		return valRet;
	}

	public String getAddress(@WebParam(name = "nodeId") String nodeId) {
		return properties.getProperty("nodes.addresses." + nodeId);
	}

	public String getCentralNodeId() {
		return properties.getProperty("nodes.central", "1");
	}

	

	public void reloadInfo() throws FileNotFoundException, IOException {
		properties.load(new FileInputStream(this.configFolder + "nodeConfig.properties"));
	}

}
