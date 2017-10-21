    public static ClassLoader createLibExtClassLoader(List<File> jarsContainerOrJars, List<URL> otherJarsOrFolder, ClassLoader parentClassLoader) 
    throws MalformedURLException
    {
        if (jarsContainerOrJars == null && otherJarsOrFolder == null) { return parentClassLoader; }
        List<URL> urls = new ArrayList<URL>();
        if (otherJarsOrFolder != null)
        {
            urls.addAll(otherJarsOrFolder);
        }
        if (jarsContainerOrJars != null)
        {
            for (File libExt : jarsContainerOrJars)
            {
                if (libExt.isDirectory())
                {
                    for (File f : libExt.listFiles())
                    {
                        if (f.getName().endsWith(".jar"))
                        {
                            // cheap to tolerate folders so let's do it.
                            URL url = f.toURI().toURL();
                            if (f.isFile())
                            {
                                // is this necessary anyways?
                                url = new URL("jar:" + url.toString() + "!/");
                            }
                            urls.add(url);
                        }
                    }
                }
            }
        }
        return new URLClassLoader(urls.toArray(new URL[urls.size()]), parentClassLoader);
    }
