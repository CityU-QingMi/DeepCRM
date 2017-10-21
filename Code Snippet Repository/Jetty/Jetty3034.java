    public Resource getResource(String path) throws MalformedURLException
    {
        if (path == null || !path.startsWith(URIUtil.SLASH))
            throw new MalformedURLException(path);

        if (_baseResource == null)
            return null;

        try
        {
            path = URIUtil.canonicalPath(path);
            Resource resource = _baseResource.addPath(path);

            if (checkAlias(path,resource))
                return resource;
            return null;
        }
        catch (Exception e)
        {
            LOG.ignore(e);
        }

        return null;
    }
