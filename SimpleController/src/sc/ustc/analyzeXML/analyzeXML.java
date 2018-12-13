package sc.ustc.analyzeXML;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class analyzeXML {
	public Map<String ,String> readXML(String passActionName,String path) {
		Map<String, String> map = new HashMap<String, String>();
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try{
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse(path);
			NodeList actionList = document.getElementsByTagName("action");
			for (int i = 0; i < actionList.getLength(); i++) {
				Node actionNode = actionList.item(i);
                //获取Node节点所有属性值
                NamedNodeMap actionNodeMap = actionNode.getAttributes();
				String actionName = actionNodeMap.getNamedItem("name").getNodeValue();
				String actionClass = actionNodeMap.getNamedItem("class").getNodeValue();
				String actionMethod = actionNodeMap.getNamedItem("method").getNodeValue();	
				if(actionName.equals(passActionName)){
					map.put("action", actionName);
					map.put("class", actionClass);
					map.put("method", actionMethod);
					//get单个节点中的子节点list
                    NodeList actionChildNodes = actionNode.getChildNodes();
					for (int j = 0; j < actionChildNodes.getLength(); j++) {

                        if (actionChildNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {

                            if (actionChildNodes.item(j).getNodeName().toString().equals("result")) {
                                NamedNodeMap resultMap = actionChildNodes.item(j).getAttributes();
                                String resultName = resultMap.getNamedItem("name").getNodeValue();
                                String resultType = resultMap.getNamedItem("type").getNodeValue();
                                String resultValue = resultMap.getNamedItem("value").getNodeValue();
                                String res = resultType+'+'+resultValue;
                        		map.put("result"+resultName, res);
                            }

                        }

                    }
				}
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}
}
