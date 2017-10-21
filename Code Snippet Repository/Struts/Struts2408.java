    public void testStatusCode() throws Exception {
        result.setParse(false);
        result.setStylesheetLocation("XSLTResultTest.xsl");
        result.setStatus("302");
        result.execute(mai);

        String out = response.getContentAsString();

        assertEquals(302, response.getStatus());
        assertTrue(out.startsWith("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"));
        assertTrue(out.indexOf("<result xmlns=\"http://www.w3.org/TR/xhtml1/strict\"") > -1);
    }
