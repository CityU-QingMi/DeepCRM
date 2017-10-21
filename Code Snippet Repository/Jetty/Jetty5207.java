    @Override
    public Resource addPath(final String subpath) throws IOException, MalformedURLException
    {
        String cpath = URIUtil.canonicalPath(subpath);

        if ((cpath == null) || (cpath.length() == 0))
            throw new MalformedURLException(subpath);

        if ("/".equals(cpath))
            return this;

        // subpaths are always under PathResource
        // compensate for input subpaths like "/subdir"
        // where default resolve behavior would be
        // to treat that like an absolute path

        return new PathResource(this, subpath);
    }
