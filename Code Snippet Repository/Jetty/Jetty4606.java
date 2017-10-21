    @Override
    public boolean create(URI uri, String location) throws IOException
    {
        Path destination = getDestination(uri,location);
        
        if (Files.isDirectory(destination))
            destination = destination.resolve(uri.getSchemeSpecificPart().substring(uri.getRawSchemeSpecificPart().lastIndexOf('/')+1));
        
        if(isFilePresent(destination))
            return false;

        download(uri,destination);

        return true;
    }
