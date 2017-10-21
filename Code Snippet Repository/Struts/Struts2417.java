    public void testTransform4WithBadDocumentInclude() throws Exception {
        result = new XSLTResult(){
            protected URIResolver getURIResolver() {
                return new URIResolver() {
                    public Source resolve(String href, String base) throws TransformerException {
                        return new StreamSource(ClassLoaderUtil.getResourceAsStream(href, this.getClass()));
                    }

                };
            }

        };
        result.setParse(false);
        result.setStylesheetLocation("XSLTResultTest4.badinclude.xsl");
        try {
            result.execute(mai);
            fail("Should have thrown an exception");
        } catch (Exception ex) {
            assertEquals("Error transforming result", ex.getMessage());
        }
    }
