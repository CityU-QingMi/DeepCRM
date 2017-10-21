    public void testTransformWithError() throws Exception {
        result = new XSLTResult(){
            protected URIResolver getURIResolver() {
                return new URIResolver() {
                    public Source resolve(String href, String base) throws TransformerException {
                        throw new TransformerException("Some random error");
                    }
                };
            }
        };
        result.setStylesheetLocation("XSLTResultTest4.xsl");
        try {
            result.execute(mai);
            fail("Should have thrown an exception");
        } catch (Exception ex) {
            assertEquals("Error transforming result", ex.getMessage());
        }
    }
