/*******************************************************************************
 * Copyright (c) 2011 Oracle. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 and Eclipse Distribution License v. 1.0
 * which accompanies this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors:
 *     Blaise Doughan - 2.2 - initial implementation
 ******************************************************************************/
package org.eclipse.persistence.testing.jaxb.xmlanyelement.ns;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.persistence.jaxb.JAXBMarshaller;
import org.eclipse.persistence.jaxb.JAXBUnmarshaller;
import org.eclipse.persistence.platform.xml.XMLPlatform;
import org.eclipse.persistence.platform.xml.XMLPlatformFactory;
import org.eclipse.persistence.testing.jaxb.JAXBWithJSONTestCases;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class DefaultNamespaceCollectionTestCases extends JAXBWithJSONTestCases {

    private final static String XML_RESOURCE = "org/eclipse/persistence/testing/jaxb/xmlanyelement/ns/root.xml";
    private final static String JSON_RESOURCE = "org/eclipse/persistence/testing/jaxb/xmlanyelement/ns/root_collection.json";

    public DefaultNamespaceCollectionTestCases(String name) throws Exception {
        super(name);
        setControlDocument(XML_RESOURCE);
        setControlJSON(JSON_RESOURCE);
        Class[] classes = new Class[1];
        classes[0] = RootWithCollection.class;
        setClasses(classes);
        Map<String, String> namespaces = new HashMap<String, String>();
        namespaces.put("urn:test","ns1");
        namespaces.put("","ns2");
        jaxbUnmarshaller.setProperty(JAXBUnmarshaller.JSON_NAMESPACE_PREFIX_MAPPER, namespaces);
    }
    
    protected JAXBMarshaller getJSONMarshaller() throws Exception{
    	   Map<String, String> namespaces = new HashMap<String, String>();
           namespaces.put("urn:test","ns1");
           namespaces.put("","ns2");
           JAXBMarshaller jsonMarshaller = (JAXBMarshaller) jaxbContext.createMarshaller();
           jsonMarshaller.setProperty(JAXBMarshaller.MEDIA_TYPE, "application/json");
           jsonMarshaller.setProperty(JAXBMarshaller.NAMESPACE_PREFIX_MAPPER, namespaces);
           return jsonMarshaller;
    }

    @Override
    protected RootWithCollection getControlObject() {
        RootWithCollection root = new RootWithCollection();

        XMLPlatform xmlPlatform = XMLPlatformFactory.getInstance().getXMLPlatform();
        Document document = xmlPlatform.createDocument();
        Element element = document.createElement("child");
        root.getChild().add(element);

        return root;
    }

}