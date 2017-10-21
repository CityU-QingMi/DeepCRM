    protected Path getDestination(URI uri, String location) throws IOException
    {
        if (location==null)
            return null;
        
        Path destination = _basehome.getBasePath(location);
        
        // now on copy/download paths (be safe above all else)
        if (destination!=null && !destination.startsWith(_basehome.getBasePath()))
            throw new IOException("For security reasons, Jetty start is unable to process file resource not in ${jetty.base} - " + location);
                
        boolean isDestDir = Files.isDirectory(destination) || !Files.exists(destination) && location.endsWith("/");
        if (isDestDir && uri!=null && uri.getSchemeSpecificPart().contains("/") && !uri.getSchemeSpecificPart().endsWith("/"))
            destination = destination.resolve(uri.getSchemeSpecificPart().substring(uri.getSchemeSpecificPart().lastIndexOf('/')+1));
        
        return destination;
    }
