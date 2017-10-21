    public void testTransform3() throws Exception {
        result.setParse(false);
        result.setStylesheetLocation("XSLTResultTest3.xsl");
        result.execute(mai);

        String out = response.getContentAsString();
        assertTrue(out.startsWith("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"));
        assertTrue(out.indexOf("<html xmlns=\"http://www.w3.org/TR/xhtml1/strict\"") > -1);
        assertTrue(out.indexOf("Hello Santa Claus how are you?") > -1);
        assertTrue(out.indexOf("WebWork in Action by Patrick and Jason") > -1);
        assertTrue(out.indexOf("XWork not in Action by Superman") > -1);
    }
