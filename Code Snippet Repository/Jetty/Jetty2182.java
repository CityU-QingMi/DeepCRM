    public Enumeration<URL> findEntries(Bundle bundle, String entryPath)
    {
        int last = entryPath.lastIndexOf('/');
        String path = last != -1 && last < entryPath.length() - 2 ? entryPath.substring(0, last) : "/";
        if (!path.startsWith("/"))
        {
            path = "/" + path;
        }
        String pattern = last != -1 && last < entryPath.length() - 2 ? entryPath.substring(last + 1) : entryPath;
        Enumeration<URL> enUrls = bundle.findEntries(path, pattern, false);
        return enUrls;
    }
