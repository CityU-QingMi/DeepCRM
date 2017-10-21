    @Override
    public HttpContent getContent(String pathInContext,int maxBufferSize)
        throws IOException
    {
        // Is the content in this cache?
        CachedHttpContent content =_cache.get(pathInContext);
        if (content!=null && (content).isValid())
            return content;
       
        // try loading the content from our factory.
        Resource resource=_factory.getResource(pathInContext);
        HttpContent loaded = load(pathInContext,resource,maxBufferSize);
        if (loaded!=null)
            return loaded;
        
        // Is the content in the parent cache?
        if (_parent!=null)
        {
            HttpContent httpContent=_parent.getContent(pathInContext,maxBufferSize);
            if (httpContent!=null)
                return httpContent;
        }
        
        return null;
    }
