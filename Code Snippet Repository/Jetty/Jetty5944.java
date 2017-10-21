    public void addClassPath(String classPath)
        throws IOException
    {
        if (classPath == null)
            return;
            
        StringTokenizer tokenizer= new StringTokenizer(classPath, ",;");
        while (tokenizer.hasMoreTokens())
        {
            Resource resource= _context.newResource(tokenizer.nextToken().trim());
            if (LOG.isDebugEnabled())
                LOG.debug("Path resource=" + resource);

            // Add the resource
            if (resource.isDirectory() && resource instanceof ResourceCollection)
                addClassPath(resource);
            else
            {
                // Resolve file path if possible
                File file= resource.getFile();
                if (file != null)
                {
                    URL url= resource.getURI().toURL();
                    addURL(url);
                }
                else if (resource.isDirectory())
                {
                    addURL(resource.getURI().toURL());
                }
                else
                {
                    if (LOG.isDebugEnabled())
                        LOG.debug("Check file exists and is not nested jar: "+resource);
                    throw new IllegalArgumentException("File not resolvable or incompatible with URLClassloader: "+resource);
                }
            }
        }
    }
