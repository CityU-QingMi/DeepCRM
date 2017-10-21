    private void convertBundleLocationToURL(BundleFileLocatorHelper locatorHelper, Bundle bundle, Set<URL> urls) throws Exception
    {
        File jasperLocation = locatorHelper.getBundleInstallLocation(bundle);
        if (jasperLocation.isDirectory())
        {
            for (File f : jasperLocation.listFiles())
            {
                if (f.getName().endsWith(".jar") && f.isFile())
                {
                    urls.add(f.toURI().toURL());
                }
                else if (f.isDirectory() && f.getName().equals("lib"))
                {
                    for (File f2 : jasperLocation.listFiles())
                    {
                        if (f2.getName().endsWith(".jar") && f2.isFile())
                        {
                            urls.add(f2.toURI().toURL());
                        }
                    }
                }
            }
            urls.add(jasperLocation.toURI().toURL());
        }
        else
        {
            urls.add(jasperLocation.toURI().toURL());
        }
    }
