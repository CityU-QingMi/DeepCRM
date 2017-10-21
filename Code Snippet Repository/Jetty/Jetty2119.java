    protected Resource getFileAsResource (String file)
    {
        Resource r = null;
        try
        {
            File asFile = new File (file);
            if (asFile.exists())
                r = Resource.newResource(asFile);
        }
        catch (Exception e)
        {
            r = null;
        } 
        return r;
    }
