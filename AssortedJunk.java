import org.w3c.dom.*; // Document. element, etc
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.io.StringWriter;

class AssortedJunk {
    // Derp.
    // Found at http://www.petefreitag.com/item/445.cfm.
    // This is basically here for testing purposes only!
    public static String xmlToString(Node node) {
        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            //initialize StreamResult with File object to save to file
            StreamResult result = new StreamResult(new StringWriter());
            DOMSource source = new DOMSource(node);
            transformer.transform(source, result);

            return result.getWriter().toString();
        } catch(Exception e) {
            // don't really care.
        }
        return null;
    }
}