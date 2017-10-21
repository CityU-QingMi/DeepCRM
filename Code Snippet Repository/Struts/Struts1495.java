    public void testRecursiveRemove() throws Exception {
        Document doc = getDoc("xml-with-location.xml");

        Element root = doc.getDocumentElement();
        LocationAttributes.remove(root, true);

        assertNull(root.getAttributeNode("loc:line"));
        assertNull(root.getAttributeNode("loc:column"));
        assertNull(root.getAttributeNode("loc:src"));

        Element kid = (Element)doc.getElementsByTagName("bar").item(0);
        assertNull(kid.getAttributeNode("loc:line"));
        assertNull(kid.getAttributeNode("loc:column"));
        assertNull(kid.getAttributeNode("loc:src"));
    }    
