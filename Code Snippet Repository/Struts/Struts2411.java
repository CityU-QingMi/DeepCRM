    public void testNoFileFound() throws Exception {
        try {
            result.setParse(false);
            result.setStylesheetLocation("nofile.xsl");
            result.execute(mai);
            fail("Should have thrown a TransformerException");
        } catch (TransformerException e) {
            // success
        }
    }
