        @Override
        public URL[] getURLs()
        {
            Set<URL> urls = new HashSet<URL>();
            
            //convert urls from antLoader
            String[] paths = antLoader.getClasspath().split(new String(new char[]{File.pathSeparatorChar}));
            if (paths != null)
            {
                for (String p:paths)
                {
                    File f = new File(p);
                    try
                    {
                        urls.add(f.toURI().toURL());   
                    }
                    catch (Exception e)
                    {
                        LOG.ignore(e);
                    }
                }
            }
            
            //add in any that may have been added to us as a URL directly
            URL[] ourURLS = super.getURLs();
            if (ourURLS != null)
            {
                for (URL u:ourURLS)
                    urls.add(u);
            }
            
            return urls.toArray(new URL[urls.size()]);
        }
