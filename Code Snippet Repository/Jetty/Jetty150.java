        protected String getSystemClassPath (ClassLoader loader) throws Exception
        {
            StringBuilder classpath=new StringBuilder();
            while (loader != null)
            {
                if (loader instanceof URLClassLoader)
                {
                    URL[] urls = ((URLClassLoader)loader).getURLs();
                    if (urls != null)
                    {
                        for (int i=0;i<urls.length;i++)
                        {
                            Resource resource = Resource.newResource(urls[i]);
                            File file=resource.getFile();
                            if (file!=null && file.exists())
                            {
                                if (classpath.length()>0)
                                    classpath.append(File.pathSeparatorChar);
                                classpath.append(file.getAbsolutePath());
                            }
                        }
                    }
                }
                else if (loader instanceof AntClassLoader)
                {
                    classpath.append(((AntClassLoader)loader).getClasspath());
                }

                loader = loader.getParent();
            }

            return classpath.toString();
        }
