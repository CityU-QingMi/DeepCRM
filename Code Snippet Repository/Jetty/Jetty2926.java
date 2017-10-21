    public void setMetaData(org.eclipse.jetty.http.MetaData.Request request)
    {
        _metaData = request;
        
        setMethod(request.getMethod());
        HttpURI uri = request.getURI();
        _originalURI = uri.isAbsolute()&&request.getHttpVersion()!=HttpVersion.HTTP_2?uri.toString():uri.getPathQuery();

        String encoded = uri.getPath();
        String path;
        if (encoded==null)
        {
            path = uri.isAbsolute()?"/":null;
            uri.setPath(path);
        }
        else if (encoded.startsWith("/"))
        {
            path = (encoded.length()==1)?"/":URIUtil.canonicalPath(URIUtil.decodePath(encoded));
        }
        else if ("*".equals(encoded) || HttpMethod.CONNECT.is(getMethod()))
        {
            path = encoded;
        }
        else
        {
            path = null;
        }

        if (path==null || path.isEmpty())
        {
            setPathInfo(encoded==null?"":encoded);
            throw new BadMessageException(400,"Bad URI");
        }
        setPathInfo(path);
    }
