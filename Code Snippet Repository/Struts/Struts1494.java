    public void testAddLocationAttributes() throws Exception {
        AttributesImpl attrs = new AttributesImpl();
        LocationAttributes.addLocationAttributes(new Locator() {
            public int getColumnNumber() { return 40; }
            public int getLineNumber() { return 1; }
            public String getSystemId() { return "path/to/file.xml"; }
            public String getPublicId() { return "path/to/file.xml"; }
        }, attrs);

        assertTrue("path/to/file.xml".equals(attrs.getValue("loc:src")));
        assertTrue("1".equals(attrs.getValue("loc:line")));
        assertTrue("40".equals(attrs.getValue("loc:column")));
    }
