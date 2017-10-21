    public void testTransformWithBadCharacter() throws Exception {
        result = new XSLTResult();
        result.setStylesheetLocation("XSLTResultTest.bad.character.xsl");
        try {
            result.execute(mai);
            fail("Should have thrown an exception");
        } catch (Exception ex) {
            assertEquals("Error transforming result", ex.getMessage());
        }
    }
