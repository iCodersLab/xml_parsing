package icoderslab.com.xmlparsing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.io.InputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


public class parser extends AppCompatActivity {


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parser);

        TextView text1 = (TextView) findViewById(R.id.name);


        try {
            InputStream in = getAssets().open("file.xml");

            DocumentBuilderFactory docb = DocumentBuilderFactory.newInstance();
            DocumentBuilder doc = docb.newDocumentBuilder();
            Document d = doc.parse(in);
            Element element = d.getDocumentElement();
            element.normalize();


            NodeList nodeList = d.getElementsByTagName("employee");
            for (int i = 0; i < nodeList.getLength(); i++) {

                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element2 = (Element) node;
                    text1.setText(text1.getText() + "\n" + getValue("name", element2) + "\n");

                }

            }
        }
            catch (Exception e) {
            e.printStackTrace();
        }


    }


    public static String getValue(String tag, Element e) {

        NodeList node = e.getElementsByTagName(tag).item(0).getChildNodes();
        Node nodes = node.item(0);
        return nodes.getNodeValue();

    } 


}
