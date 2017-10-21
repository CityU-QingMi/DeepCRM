    public void testSimpleGetTexts() throws Exception {
        assertEquals("Hello World", tp.getText("hello"));
        assertEquals(null, tp.getText("not.in.bundle"));

        assertEquals("Hello World", tp.getText("hello", "this is default"));
        assertEquals("this is default", tp.getText("not.in.bundle", "this is default"));

        List<Object> nullList = null;
        assertEquals("Hello World", tp.getText("hello", nullList));

        String[] nullStrings = null;
        assertEquals("Hello World", tp.getText("hello", nullStrings));
    }
