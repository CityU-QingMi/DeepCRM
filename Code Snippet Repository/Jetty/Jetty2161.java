    public static ClassLoader createLibEtcClassLoader(File jettyHome, ClassLoader parentClassLoader) throws MalformedURLException
    {
        if (jettyHome == null) { return parentClassLoader; }
        ArrayList<URL> urls = new ArrayList<URL>();
        File jettyResources = new File(jettyHome, "resources");
        if (jettyResources.exists())
        {
            // make sure it contains something else than README:
            Map<String, File> jettyResFiles = new HashMap<String, File>();
            for (File f : jettyResources.listFiles())
            {
                jettyResFiles.put(f.getName(), f);
                if (f.getName().toLowerCase(Locale.ENGLISH).startsWith("readme"))
                {
                    continue;
                }
                else
                {
                    if (urls.isEmpty())
                    {
                        urls.add(jettyResources.toURI().toURL());
                    }
                }
            }
            processFilesInResourcesFolder(jettyHome, jettyResFiles);
        }
        File libExt = new File(jettyHome, "lib/ext");
        if (libExt.exists())
        {
            for (File f : libExt.listFiles())
            {
                if (f.getName().endsWith(".jar"))
                {
                    // cheap to tolerate folders so let's do it.
                    URL url = f.toURI().toURL();
                    if (f.isFile())
                    {// is this necessary anyways?
                        url = new URL("jar:" + url.toString() + "!/");
                    }
                    urls.add(url);
                }
            }
        }

        return new URLClassLoader(urls.toArray(new URL[urls.size()]), parentClassLoader);
    }
