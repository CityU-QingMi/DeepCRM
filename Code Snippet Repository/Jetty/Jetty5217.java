    public static Resource newResource(String resource, boolean useCaches)       
        throws MalformedURLException, IOException
    {
        URL url=null;
        try
        {
            // Try to format as a URL?
            url = new URL(resource);
        }
        catch(MalformedURLException e)
        {
            if(!resource.startsWith("ftp:") &&
               !resource.startsWith("file:") &&
               !resource.startsWith("jar:"))
            {
                try
                {
                    // It's a file.
                    if (resource.startsWith("./"))
                        resource=resource.substring(2);
                    File file=new File(resource).getCanonicalFile();
                    return new PathResource(file);
                }
                catch(IOException e2)
                {
                    e2.addSuppressed(e);
                    throw e2;
                }
            }
            else
            {
                LOG.warn("Bad Resource: "+resource);
                throw e;
            }
        }

        return newResource(url, useCaches);
    }
