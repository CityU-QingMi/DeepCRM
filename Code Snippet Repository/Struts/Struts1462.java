    public void testParse() throws Exception {
        InputSource in = new InputSource(new StringReader(xml));
        in.setSystemId("foo://bar");
        
        Document doc = DomHelper.parse(in);
        assertNotNull(doc);
        assertTrue("Wrong root node",
            "foo".equals(doc.getDocumentElement().getNodeName()));
        
        NodeList nl = doc.getElementsByTagName("bar");
        assertTrue(nl.getLength() == 1);
        
        
        
    }
