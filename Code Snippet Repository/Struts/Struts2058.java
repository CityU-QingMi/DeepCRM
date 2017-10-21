    protected void setUp() throws Exception {
        super.setUp();
        
        // prepare to parse the TLD file using DOM
        DocumentBuilderFactory factory 
            = DocumentBuilderFactory.newInstance();

        DocumentBuilder builder ;
        try {
            builder = factory.newDocumentBuilder();
        }
        catch (Exception e) {
            e.printStackTrace();
            return;
        }
        
        URL s2Url = this.getClass().getResource("/META-INF/struts-tags.tld");
        if (s2Url == null ) {
            fail("unable to find struts-tags.tld");
        }
        File tldFile = new File(s2Url.toURI());
        doc = builder.parse(tldFile);
        
    }
