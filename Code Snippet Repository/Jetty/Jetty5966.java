        @Override
        public URL getResource(String path) throws MalformedURLException
        {
            Resource resource=WebAppContext.this.getResource(path);
            if (resource==null || !resource.exists())
                return null;

            // Should we go to the original war?
            if (resource.isDirectory() && resource instanceof ResourceCollection && !WebAppContext.this.isExtractWAR())
            {
                Resource[] resources = ((ResourceCollection)resource).getResources();
                for (int i=resources.length;i-->0;)
                {
                    if (resources[i].getName().startsWith("jar:file"))
                        return resources[i].getURI().toURL();
                }
            }

            return resource.getURI().toURL();
        }
