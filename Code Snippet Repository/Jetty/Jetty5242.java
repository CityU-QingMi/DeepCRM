    @Override
    public Resource addPath(String path)
        throws IOException,MalformedURLException
    {
        if (path==null)
            return null;

        path = URIUtil.canonicalPath(path);

        return newResource(URIUtil.addEncodedPaths(_url.toExternalForm(),URIUtil.encodePath(path)), _useCaches);
    }
