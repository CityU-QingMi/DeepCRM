    public void testEncoding() throws Exception {
        result.setParse(false);
        result.setStylesheetLocation("XSLTResultTest.xsl");
        result.setEncoding("ISO-8859-1");
        result.execute(mai);

        String actual = response.getCharacterEncoding();

        assertEquals(actual, "ISO-8859-1");
    }
