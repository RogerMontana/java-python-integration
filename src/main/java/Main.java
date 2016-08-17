import java.util.Properties;

import org.python.util.PythonInterpreter;
import org.python.core.*;

public class Main {


	public static void main(String a[]) {

		String invalidJson = "{\"id\": \"123\", \"ua\":\"Mozilla/firefox lg4.5\" 1.2123\"}";

		Properties props = new Properties();
		props.put("python.home", "/usr/lib/python2.7");
		props.put("python.console.encoding",
				"UTF-8");
		props.put("python.security.respectJavaAccessibility",
				"false");
		props.put("python.import.site", "false");


		Properties preprops = System.getProperties();

		PythonInterpreter.initialize(preprops, props, new String[0]);


		PythonInterpreter python = new PythonInterpreter();


		PyObject jsonInvalid = new PyString(invalidJson);
		python.execfile("/json.py");
		PyFunction pyFuntion = (PyFunction) python.get("parse_invalid_json", PyFunction.class);
		PyObject resultFromScript = pyFuntion.__call__(jsonInvalid);

		System.out.println("val : " + resultFromScript.toString());
	}

}
