    private void addUniquePropertyFile(String propertyFileRef, Path propertyFile) throws IOException
    {
        if (!FS.canReadFile(propertyFile))
        {
            throw new IOException("Cannot read file: " + propertyFileRef);
        }
        propertyFile = FS.toRealPath(propertyFile);
        if (!propertyFiles.contains(propertyFile))
        {
            propertyFiles.add(propertyFile);
        }
    }
