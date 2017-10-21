    public Node.Nodes parseTagFileDirectives(String inFileName,
            URL tagFileJarUrl)
            throws FileNotFoundException, JasperException, IOException {
        boolean isTagFileSave = isTagFile;
        boolean directiveOnlySave = directiveOnly;
        isTagFile = true;
        directiveOnly = true;
        Node.Nodes page = doParse(inFileName, null, tagFileJarUrl);
        directiveOnly = directiveOnlySave;
        isTagFile = isTagFileSave;
        return page;
    }
