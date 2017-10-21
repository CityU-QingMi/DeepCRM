    @Override
    public boolean isContainedIn (Resource resource) 
    throws MalformedURLException
    {
        String string = _urlString;
        int index = string.lastIndexOf("!/");
        if (index > 0)
            string = string.substring(0,index);
        if (string.startsWith("jar:"))
            string = string.substring(4);
        URL url = new URL(string);
        return url.sameFile(resource.getURI().toURL());     
    }
