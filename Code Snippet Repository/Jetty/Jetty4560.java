    private void addUniqueXmlFile(String xmlRef, Path xmlfile) throws IOException
    {
        if (!FS.canReadFile(xmlfile))
        {
            throw new IOException("Cannot read file: " + xmlRef);
        }
        xmlfile = FS.toRealPath(xmlfile);
        if (!xmls.contains(xmlfile))
        {
            xmls.add(xmlfile);
        }
    }
