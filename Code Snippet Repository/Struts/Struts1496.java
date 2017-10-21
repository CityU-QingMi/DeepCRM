    public void testNonRecursiveRemove() throws Exception {
        Document doc = getDoc("xml-with-location.xml");

        Element root = doc.getDocumentElement();
        LocationAttributes.remove(root, false);

        assertNull(root.getAttributeNode("loc:line"));
        assertNull(root.getAttributeNode("loc:column"));
        assertNull(root.getAttributeNode("loc:src"));

        Element kid = (Element)doc.getElementsByTagName("bar").item(0);
        assertNotNull(kid.getAttributeNode("loc:line"));
        assertNotNull(kid.getAttributeNode("loc:column"));
        assertNotNull(kid.getAttributeNode("loc:src"));
    }    
