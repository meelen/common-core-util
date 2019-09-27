import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * 1、bl的dubbo组件参数问题
 * 
 * @author even
 *
 */
public class IfpBlFileUtil {
	private static String dubboFlowAction = "dubboFlowAction";

	public static void main(String[] args) {
		// String path =
		// "D:\\Documents\\00_work\\易诚互动\\创兴银行\\soa\\maven\\service-center\\designSource\\common\\";
		String path = "D:\\Documents\\00_work\\易诚互动\\创兴银行\\soa\\maven\\service-center\\designSource\\bl";
		/*
		 * <action id="dubboFlowAction0" type="dubboFlowAction" x="-96" y="187"
		 * w="85" h="40"> <conf> <property name="logicId" value="BAS00020"/>
		 * </conf> <transition name="dubboFlowAction0.transition2"
		 * value="transitionAction5"/> </action>
		 */

		File file = new File(path);

		iteratorFile(file);
	}

	/**
	 * 遍历文件
	 * 
	 * @param file
	 */
	public static void iteratorFile(File file) {
		if (file.isDirectory()) {
			File next[] = file.listFiles();
			for (int i = 0; i < next.length; i++) {
				iteratorFile(next[i]);
			}
		}else{
			mergeAcitonSet(file);
		}
	}

	/**
	 * 合并 ifp actionSet
	 * 
	 * @param args
	 */
	public static void mergeAcitonSet(File file) {
		SAXReader saxReader = new SAXReader();
		try {
			Document document = saxReader.read(file);
			// 获取根元素 flow
			Element root = document.getRootElement();
			Element flow = root.element("flow");
			// 获取所有子元素 action
			List<Element> actionList = flow.elements();
			for (Element action : actionList) {
				String actionId = action.attributeValue("id");
				if (actionId.startsWith(dubboFlowAction)) {
					// 获取元素 conf
					Element conf = action.element("conf");
					if (conf.elements().size() < 4) {
						System.out.println(file.getName());
						Element mappingId = DocumentHelper.createElement("property");
						mappingId.addAttribute("name", "mappingId");
						mappingId.addAttribute("value", "");
						Element errorCodeField = DocumentHelper.createElement("property");
						errorCodeField.addAttribute("name", "errorCodeField");
						errorCodeField.addAttribute("value", "");
						Element errorMsgField = DocumentHelper.createElement("property");
						errorMsgField.addAttribute("name", "errorMsgField");
						errorMsgField.addAttribute("value", "");
						conf.add(mappingId);
						conf.add(errorCodeField);
						conf.add(errorMsgField);
					}
				}
			}
			// 输出到文件
			// 格式: 设置缩进为4个空格，并且另起一行为 true
			OutputFormat format = new OutputFormat("", false);
			XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(file.getPath()), format);
			xmlWriter.write(document);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
