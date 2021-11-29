import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

public class XMLCreate {
    /**
     * Create and Stores the XML on a path specified.
     *
     * @param path The file path where the prepared XML will be stored.
     * @param expression The Input Expression containing all the operands and values.
     * @return true or false if xml is serialized successfully.
     */
    public static boolean SerializeToXml(String path, Expression expression) {
        File outFile = new File(path);
        OutputStreamWriter writer;
        try {
            writer = new OutputStreamWriter(new FileOutputStream(outFile));
            // Create document factory
            DocumentBuilderFactory docFact = DocumentBuilderFactory
                    .newInstance();
            DocumentBuilder docBuilder;
            // Build document
            docBuilder = docFact.newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            // Create root node
            Element root = doc.createElement("root");
            doc.appendChild(root);

            Element xml = GetXml(doc,expression);
            // Append xml content to root node
            root.appendChild(xml);

            // set up a transformer
            TransformerFactory transfac = TransformerFactory.newInstance();
            Transformer trans = transfac.newTransformer();
            trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            trans.setOutputProperty(OutputKeys.INDENT, "yes");

            // create string from xml tree
            StreamResult result = new StreamResult(writer);
            DOMSource source = new DOMSource(doc);
            trans.transform(source, result);
            writer.flush();
            writer.close();
        } catch (ParserConfigurationException e) {
            System.out.println("Configuration error: Unable to create document builder");
            return false;
        } catch (TransformerConfigurationException e) {
            System.out.println("Configuration error: Unable to create transformer");
            return false;
        } catch (TransformerException e) {
            System.out.println("Error while transforming XML document");
            return false;
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            return false;
        } catch (IOException e) {
            System.out.println("IO error");
            return false;
        }
        return true;
    }

    /**
     * Return the XML document
     *
     * @param document XML Document containing the evaluated Expression
     * @param expression Input Expression
     * @return XML Element
     */
    private static Element GetXml(Document document, Expression expression) {
        if (expression.isUnary()) {
            Element elem = document.createElement("Value");
            elem.appendChild(document.createTextNode(String
                    .valueOf(expression.getLeftValue())));
            return elem;
        } else if (expression.getLeft() != null
                && expression.getRight() != null) {
            Element elem = document.createElement(XmlGetNodeName(expression));
            elem.appendChild(GetXml(document,expression.getLeft()));
            elem.appendChild(GetXml(document,expression.getRight()));
            return elem;
        } else if (expression.getLeft() == null
                && expression.getRight() == null) {
            Element elem = document.createElement(XmlGetNodeName(expression));
            elem.appendChild(GetXml(document,new Expression(expression.getLeftValue())));
            elem.appendChild(GetXml(document,new Expression(expression.getRightValue())));
            return elem;
        } else {
            // We should never get here
            System.out.println("Invalid state!");
            return null;
        }
    }

    /**
     * Get the Approriate Node name to render in the file
     *
     * @param expression Input Expreesion to fetch it's Op.
     * @return The node name. eg: + for Add
     */
    private static String XmlGetNodeName(Expression expression) {
        switch (expression.getOp()) {
            case "+":
                return "Add";
            case "-":
                return "Subtract";
            case "*":
                return "Multiply";
            case "/":
                return "Divide";
        }
        // We should never get here
        System.out.println("Invalid state!");
        return null;
    }
}
