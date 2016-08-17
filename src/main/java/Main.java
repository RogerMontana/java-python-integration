import java.util.Properties;

import org.python.util.PythonInterpreter;
import org.python.core.*;

public class Main {


	public static void main(String a[]) {

		String jsonToFix = "{\"id\": \"123\", \"ua\":\"Mozilla/firefox lg4.5\" 1.2123\"}";

		Properties userProps = new Properties();
		userProps.put("python.home", "/usr/lib/python2.7");
		userProps.put("python.console.encoding",
				"UTF-8");
		userProps.put("python.security.respectJavaAccessibility",
				"false");
		userProps.put("python.import.site", "false");

		Properties sysProps = System.getProperties();

		PythonInterpreter.initialize(sysProps, userProps, new String[0]);

		PythonInterpreter python = new PythonInterpreter();

		PyObject pyObjectJson = new PyString(jsonToFix);
		python.execfile("src/main/resources/json.py");
		PyFunction pyFuntion = (PyFunction) python.get("parse_invalid_json", PyFunction.class);
		PyObject resultFromScript = pyFuntion.__call__(pyObjectJson);

		System.out.println("val : " + resultFromScript.toString());
	}

}
