    public static void dump(ClassLoader loader)
    {
        while (loader != null)
        {
            System.err.println(loader);
            if (loader instanceof URLClassLoader)
            {
                URL[] urls = ((URLClassLoader)loader).getURLs();
                if (urls != null)
                {
                    for (URL u:urls)
                        System.err.println("\t"+u+"\n");
                }
            }
            loader = loader.getParent();
        }
    }
