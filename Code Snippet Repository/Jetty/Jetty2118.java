    protected Resource getFileAsResource (String dir, String file)
    {
        Resource r = null;
        try
        {
            File asFile = new File (dir, file);
            if (asFile.exists())
                r = Resource.newResource(asFile);
        }
        catch (Exception e)
        {
            r = null;
        } 
        return r;
    }
