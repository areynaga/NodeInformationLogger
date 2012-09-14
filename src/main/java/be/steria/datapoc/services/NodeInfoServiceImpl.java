package be.steria.datapoc.services;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.jws.WebParam;
import javax.jws.WebService;


@SuppressWarnings("restriction")
@WebService
public class NodeInfoServiceImpl implements NodeInfoService {

	private String configFolder;
	
	private List<String> nodeAddresses;
	
	private int centralNode;
	
	
	private void loadProperties() throws Exception {
		Properties configProperties = new Properties();
		String propValue = null;
		int idNode = 0;
		Properties properties;
		properties = new Properties();
		configProperties.load(this.getClass().getClassLoader()
					.getResourceAsStream("config.properties"));
		configFolder = configProperties.getProperty("config.folder");
		
		nodeAddresses = new ArrayList<String>();
		centralNode = 0;
		try {
			properties.load(new FileInputStream(configFolder + "nodeConfig.properties"));
		} catch (Exception x) {
			return;
		}
		
		centralNode = Integer.parseInt(properties.getProperty("nodes.centralNode"));
		propValue = properties.getProperty("nodes.addresses." + idNode);
		while (propValue != null) {
			
			nodeAddresses.add(propValue);
			idNode++;
			
			propValue = properties.getProperty("nodes.addresses." + idNode);
		}
	}
	
	public NodeInfoServiceImpl() throws Exception {
		loadProperties();
	}
	
	public List<String> getNodeList() {
		
		List<String> valRet = new ArrayList<String>();
		for (int i=0; i<nodeAddresses.size(); i++) 
			valRet.add(String.valueOf(i));
		return valRet;
		
	}

	public String getAddress(@WebParam(name = "nodeId") String nodeId) {
		return nodeAddresses.get(Integer.parseInt(nodeId));
	}

	public String getCentralNodeId() {
		return String.valueOf(centralNode);
	}

	public void reloadFile() throws Exception {
		loadProperties();
	}
	
	public void configure(int centralNode, List<String> nodeAddresses) throws Exception{
		if (!(0 <= centralNode && centralNode < nodeAddresses.size()))
			throw new Exception("The following predicate is not true: 0 <= centralNode < nodeAddresses.size()");
		
		this.nodeAddresses = nodeAddresses;
		this.centralNode = centralNode;
	}


}
