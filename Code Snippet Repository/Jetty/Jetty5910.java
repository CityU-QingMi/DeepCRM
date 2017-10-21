    public void matched (URI uri)
    throws Exception
    {
        LOG.debug("Search of {}",uri);
        if (uri.toString().toLowerCase(Locale.ENGLISH).endsWith(".jar"))
        {
         
            InputStream in = Resource.newResource(uri).getInputStream();
            if (in==null)
                return;

            JarInputStream jar_in = new JarInputStream(in);
            try
            { 
                JarEntry entry = jar_in.getNextJarEntry();
                while (entry!=null)
                {
                    processEntry(uri, entry);
                    entry = jar_in.getNextJarEntry();
                }
            }
            finally
            {
                jar_in.close();
            }   
        }
    }
