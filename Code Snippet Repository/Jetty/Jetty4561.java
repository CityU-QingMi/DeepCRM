    public void resolveExtraXmls() throws IOException
    {
        // Find and Expand XML files
        for (String xmlRef : xmlRefs)
        {
            // Straight Reference
            Path xmlfile = baseHome.getPath(xmlRef);
            if (!FS.exists(xmlfile))
            {
                xmlfile = baseHome.getPath("etc/" + xmlRef);
            }
            addUniqueXmlFile(xmlRef,xmlfile);
        }
    }
