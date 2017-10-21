    public void resolvePropertyFiles() throws IOException
    {
        // Find and Expand property files
        for (String propertyFileRef : propertyFileRefs)
        {
            // Straight Reference
            Path propertyFile = baseHome.getPath(propertyFileRef);
            if (!FS.exists(propertyFile))
            {
                propertyFile = baseHome.getPath("etc/" + propertyFileRef);
            }
            addUniquePropertyFile(propertyFileRef,propertyFile);
        }
    }
