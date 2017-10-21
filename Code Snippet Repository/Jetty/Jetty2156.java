    public static Resource findDir (Bundle bundle, String dir)
    {
        if (bundle == null)
            return null;

        try
        {
            File f = BundleFileLocatorHelperFactory.getFactory().getHelper().getBundleInstallLocation(bundle);
            URL u = f.toURI().toURL();
            u = BundleFileLocatorHelperFactory.getFactory().getHelper().getLocalURL(u);
            Resource res = Resource.newResource(u);
            String s = res.toString();
   
            //check if it is an unarchived bundle
            if (s.endsWith(".jar") && s.startsWith("file:"))
                res = JarResource.newJarResource(res);
            
            //if looking for a directory 
            if (dir != null)
                res = res.addPath(dir);
            
            return res;
          
        }
        catch (Exception e)
        {
            LOG.warn("Bad bundle location" , e);
            return null;
        }
    }
