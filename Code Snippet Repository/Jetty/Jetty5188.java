    @Override
    public Resource addPath(String path)
            throws IOException, MalformedURLException
    {
        assertValidPath(path);
        path = org.eclipse.jetty.util.URIUtil.canonicalPath(path);

        if (path==null)
            throw new MalformedURLException();
        
        if ("/".equals(path))
            return this;

        return new FileResource(_file, path);
    }
