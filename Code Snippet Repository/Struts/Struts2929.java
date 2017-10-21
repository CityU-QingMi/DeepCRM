    public Node.Nodes parseDirectives(String inFileName)
    throws FileNotFoundException, JasperException, IOException {
        // If we're parsing a packaged tag file or a resource included by it
        // (using an include directive), ctxt.getTagFileJar() returns the 
        // JAR file from which to read the tag file or included resource,
        // respectively.
        isTagFile = ctxt.isTagFile();
        directiveOnly = true;
        return doParse(inFileName, null, ctxt.getTagFileJarUrl());
    }
