    private  List<Resource> getBundleAsResource(Bundle bundle)
    throws Exception
    {
        List<Resource> resources = new ArrayList<Resource>();

        File file = BundleFileLocatorHelperFactory.getFactory().getHelper().getBundleInstallLocation(bundle);
        if (file.isDirectory())
        {
            for (File f : file.listFiles())
            {
                if (f.getName().endsWith(".jar") && f.isFile())
                {
                    resources.add(Resource.newResource(f));
                }
                else if (f.isDirectory() && f.getName().equals("lib"))
                {
                    for (File f2 : file.listFiles())
                    {
                        if (f2.getName().endsWith(".jar") && f2.isFile())
                        {
                            resources.add(Resource.newResource(f));
                        }
                    }
                }
            }
            resources.add(Resource.newResource(file)); //TODO really???
        }
        else
        {
            resources.add(Resource.newResource(file));
        }
        
        return resources;
    }
