    public void testParse() throws Exception {
        String str = "<map:generate> - path/to/file.xml:1:40";
        Location loc = LocationUtils.parse(str);
        
        assertEquals("<map:generate>", loc.getDescription());
        assertEquals("URI", "path/to/file.xml", loc.getURI());
        assertEquals("line", 1, loc.getLineNumber());
        assertEquals("column", 40, loc.getColumnNumber());
        assertEquals("string representation", str, loc.toString());
    }
