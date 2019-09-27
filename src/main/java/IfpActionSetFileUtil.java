import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * 文件合并
 * 
 * @author even
 *
 */
public class IfpActionSetFileUtil {
	public static void main(String[] args) {
		String path = "D:\\Documents\\00_work\\易诚互动\\创兴银行\\soa\\maven\\service-center\\designSource\\common\\";
		String path1 = path + "actionSet.xml";
		// actionSettrans actionSetsecurity actionSetuser
		String path2 = path + "actionSetuser.xml";
		mergeAcitonSet(path1, path2);
	}

	/**
	 * 合并 ifp actionSet
	 * 
	 * @param args
	 */
	public static void mergeAcitonSet(String path1, String path2) {
		Map<String, Element> setMap1 = unformatSet(path1);
		Map<String, Element> setMap2 = unformatSet(path2);

		Map<String, Map<String, Element>> actionMap1 = unformatAction(path1);
		Map<String, Map<String, Element>> actionMap2 = unformatAction(path2);

		Map<String, Element> diffSetMap = diffSet(setMap1, setMap2);
		Map<String, Map<String, Element>> diffActionMap = diffAction(actionMap1, actionMap2);

		SAXReader saxReader = new SAXReader();
		try {
			// <actionSet><set id="blBase"><action id="setCollValueAction">
			Document document = saxReader.read(new File(path1));
			// 获取根元素
			Element root = document.getRootElement();

			// 获取所有子元素set, 添加新元素action
			List<Element> setList = root.elements();
			for (Element set : setList) {
				String setId = set.attributeValue("id");
				Map<String, Element> actionMap = diffActionMap.get(setId);
				if(actionMap != null){
					for (Map.Entry<String, Element> entry : actionMap.entrySet()) {
						set.add((Element) entry.getValue().clone());
					}
				}
			}
			
			// 添加新元素set
			for (Map.Entry<String, Element> entry : diffSetMap.entrySet()) {
				root.add((Element) entry.getValue().clone());
			}
			// 输出
			// 输出到控制台
			// XMLWriter xmlWriter = new XMLWriter();
			// xmlWriter.write(document);

			// 输出到文件
			// 格式: 设置缩进为4个空格，并且另起一行为 true
			OutputFormat format = new OutputFormat("    ", true);
			XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(path1), format);
			xmlWriter.write(document);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static Map<String, Map<String, Element>> unformatAction(String path) {
		Map<String, Map<String, Element>> setMap = new HashMap<>();
		SAXReader saxReader = new SAXReader();
		try {
			// <actionSet><set id="blBase"><action id="setCollValueAction">
			Document document = saxReader.read(new File(path));
			// 获取根元素
			Element root = document.getRootElement();
			// 获取所有子元素set
			List<Element> setList = root.elements();
			for (Element set : setList) {
				String setId = set.attributeValue("id");
				Map<String, Element> actionMap = new HashMap<>();
				// 获取所有子元素 action
				List<Element> actionList = set.elements();
				for (Element action : actionList) {
					String actionId = action.attributeValue("id");
					actionMap.put(actionId, action);
				}
				setMap.put(setId, actionMap);
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return setMap;
	}

	public static Map<String, Element> unformatSet(String path) {
		Map<String, Element> setMap = new HashMap<>();
		SAXReader saxReader = new SAXReader();
		try {
			// <actionSet><set id="blBase"><action id="setCollValueAction">
			Document document = saxReader.read(new File(path));
			// 获取根元素
			Element root = document.getRootElement();
			// 获取所有子元素set
			List<Element> setList = root.elements();
			for (Element set : setList) {
				String setId = set.attributeValue("id");
				setMap.put(setId, set);
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return setMap;
	}

	/**
	 * 返回setMap2有setMap1没有的差集
	 * 
	 * @param setMap1
	 * @param setMap2
	 */
	public static Map<String, Element> diffSet(Map<String, Element> setMap1, Map<String, Element> setMap2) {
		Map<String, Element> diffSetMap = new HashMap<>();
		for (Map.Entry<String, Element> setEntry2 : setMap2.entrySet()) {
			String setId = setEntry2.getKey();
			if (setMap1.get(setId) == null) {
				diffSetMap.put(setId, setEntry2.getValue());
			}
		}
		return diffSetMap;
	}

	/**
	 * 返回actionMap2有actionMap1没有的差集
	 * 
	 * @param actionMap1
	 * @param actionMap2
	 * @return
	 */
	public static Map<String, Map<String, Element>> diffAction(Map<String, Map<String, Element>> actionMap1, Map<String, Map<String, Element>> actionMap2) {
		Map<String, Map<String, Element>> diffActionMap = new HashMap<>();
		for (Map.Entry<String, Map<String, Element>> actionEntry2 : actionMap2.entrySet()) {
			String setId = actionEntry2.getKey();
			if (actionMap1.get(setId) != null) {
				Map<String, Element> acitonSetMap1 = actionMap1.get(setId);
				Map<String, Element> acitonSetMap2 = actionEntry2.getValue();
				Map<String, Element> diffAction = new HashMap<>();
				for (Map.Entry<String, Element> acitonSetEntry2 : acitonSetMap2.entrySet()) {
					String actionId = acitonSetEntry2.getKey();
					if (acitonSetMap1.get(actionId) == null) {
						diffAction.put(actionId, acitonSetEntry2.getValue());
					}
				}
				diffActionMap.put(setId, diffAction);
			}
		}
		return diffActionMap;
	}
}
