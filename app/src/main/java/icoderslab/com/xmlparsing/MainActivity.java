package icoderslab.com.xmlparsing;


/*
*
* Created By Abeer Qamer
*
*
* */

import java.io.InputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.javatpoint.domxmlparsing.R;

public class MainActivity extends Activity {
    TextView tv1; // textview object

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1=(TextView)findViewById(R.id.textView1);
        try {
            InputStream is = getAssets().open("file.xml"); // the xml file to parse


            // DOM   = Document Object Model
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            Document doc = dBuilder.parse(is);
            Element element=doc.getDocumentElement();
            element.normalize();

            // used with DOM to grasp data from the xml file
            NodeList nList = doc.getElementsByTagName("employee");

            // this'll run for the entire items inside the file
            for (int i=0; i<nList.getLength(); i++) {

                Node node = nList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element2 = (Element) node;
                    tv1.setText(tv1.getText()+"\n\nName : " + getValue("name", element2)+"\n");
                    tv1.setText(tv1.getText()+"Profession : " + getValue("profession", element2)+"\n");
                    tv1.setText(tv1.getText()+"---------------------------");
                }
            }
        } catch (Exception e) {e.printStackTrace();}

    }

    // to retrieve the value getValue() is used.
    private static String getValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
    }

}
