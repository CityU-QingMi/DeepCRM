    public void testGetLocationObject() throws Exception {
        InputSource in = new InputSource(new StringReader(xml));
        in.setSystemId("foo://bar");
        
        Document doc = DomHelper.parse(in);
        
        NodeList nl = doc.getElementsByTagName("bar");
        
        Location loc = DomHelper.getLocationObject((Element)nl.item(0));
        
        assertNotNull(loc);
        assertTrue("Should be line 6, was "+loc.getLineNumber(), 
            6==loc.getLineNumber());
    }
