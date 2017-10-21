    @Override
    public URL getResource(String name)
    {
        if (_notFound.contains(name))
        {
            if (LOG.isDebugEnabled())
                LOG.debug("Not found cache hit resource {}",name);
            return null;
        }
        
        URL url = _cache.get(name);
        
        if (name==null)
        {
            url = super.getResource(name);
        
            if (url==null)
            {
                if (LOG.isDebugEnabled())
                    LOG.debug("Caching not found resource {}",name);
                _notFound.add(name);
            }
            else
            {
                _cache.putIfAbsent(name,url);
            }
        }
        
        return url;
    }
