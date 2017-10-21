    public File getFileInBundle(Bundle bundle, String path) throws Exception
    {
        if (path != null && path.length() > 0 && path.charAt(0) == '/')
        {
            path = path.substring(1);
        }
        File bundleInstall = getBundleInstallLocation(bundle);
        File webapp = path != null && path.length() != 0 ? new File(bundleInstall, path) : bundleInstall;
        if (!webapp.exists()) { throw new IllegalArgumentException("Unable to locate " + path
                                                                   + " inside "
                                                                   + bundle.getSymbolicName()
                                                                   + " ("
                                                                   + (bundleInstall != null ? bundleInstall.getAbsolutePath() : " no_bundle_location ")
                                                                   + ")"); }
        return webapp;
    }
