    @Override
    public void push()
    {
        if (HttpMethod.POST.is(_method) || HttpMethod.PUT.is(_method))
            throw new IllegalStateException("Bad Method "+_method);

        if (_path==null || _path.length()==0)
            throw new IllegalStateException("Bad Path "+_path);

        String path=_path;
        String query=_queryString;
        int q=path.indexOf('?');
        if (q>=0)
        {
            query=(query!=null && query.length()>0)?(path.substring(q+1)+'&'+query):path.substring(q+1);
            path=path.substring(0,q);
        }

        if (!path.startsWith("/"))
            path=URIUtil.addPaths(_request.getContextPath(),path);

        String param=null;
        if (_sessionId!=null)
        {
            if (_request.isRequestedSessionIdFromURL())
                param="jsessionid="+_sessionId;
            // TODO else
            //      _fields.add("Cookie","JSESSIONID="+_sessionId);
        }

        if (_conditional)
        {
            if (_etag!=null)
                _fields.add(HttpHeader.IF_NONE_MATCH,_etag);
            else if (_lastModified!=null)
                _fields.add(HttpHeader.IF_MODIFIED_SINCE,_lastModified);
        }

        HttpURI uri = HttpURI.createHttpURI(_request.getScheme(),_request.getServerName(),_request.getServerPort(),path,param,query,null);
        MetaData.Request push = new MetaData.Request(_method,uri,_request.getHttpVersion(),_fields);

        if (LOG.isDebugEnabled())
            LOG.debug("Push {} {} inm={} ims={}",_method,uri,_fields.get(HttpHeader.IF_NONE_MATCH),_fields.get(HttpHeader.IF_MODIFIED_SINCE));

        _request.getHttpChannel().getHttpTransport().push(push);
        _path=null;
        _etag=null;
        _lastModified=null;
    }
