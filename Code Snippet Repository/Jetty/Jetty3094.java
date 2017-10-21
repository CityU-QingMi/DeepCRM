    @Override
    public Resource getResource(String path)
    {
        if (LOG.isDebugEnabled())
            LOG.debug("{} getResource({})",_context == null?_baseResource:_context,_baseResource,path);

        if (path == null || !path.startsWith("/"))
            return null;

        try
        {
            Resource r = null;

            if (_baseResource != null)
            {
                path = URIUtil.canonicalPath(path);
                r = _baseResource.addPath(path);

                if (r != null && r.isAlias() && (_context == null || !_context.checkAlias(path,r)))
                {
                    if (LOG.isDebugEnabled())
                        LOG.debug("resource={} alias={}",r,r.getAlias());
                    return null;
                }
            }
            else if (_context != null)
                r = _context.getResource(path);

            if ((r == null || !r.exists()) && path.endsWith("/jetty-dir.css"))
                r = getStylesheet();

            return r;
        }
        catch (Exception e)
        {
            LOG.debug(e);
        }

        return null;
    }
