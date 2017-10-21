    public File[] locateJarsInsideBundle(Bundle bundle) throws Exception
    {
        File jasperLocation = getBundleInstallLocation(bundle);
        if (jasperLocation.isDirectory())
        {
            // try to find the jar files inside this folder
            ArrayList<File> urls = new ArrayList<File>();
            for (File f : jasperLocation.listFiles())
            {
                if (f.getName().endsWith(".jar") && f.isFile())
                {
                    urls.add(f);
                }
                else if (f.isDirectory() && f.getName().equals("lib"))
                {
                    for (File f2 : jasperLocation.listFiles())
                    {
                        if (f2.getName().endsWith(".jar") && f2.isFile())
                        {
                            urls.add(f2);
                        }
                    }
                }
            }
            return urls.toArray(new File[urls.size()]);
        }
        else
        {
            return new File[] { jasperLocation };
        }
    }
