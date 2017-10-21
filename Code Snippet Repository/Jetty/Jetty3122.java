    @Override
    public Deflater getDeflater(Request request, long content_length)
    {
        String ua = request.getHttpFields().get(HttpHeader.USER_AGENT);
        if (ua!=null && !isAgentGzipable(ua))
        {
            LOG.debug("{} excluded user agent {}",this,request);
            return null;
        }
        
        if (content_length>=0 && content_length<_minGzipSize)
        {
            LOG.debug("{} excluded minGzipSize {}",this,request);
            return null;
        }

        // check the accept encoding header
        HttpField accept = request.getHttpFields().getField(HttpHeader.ACCEPT_ENCODING);

        if (accept==null)
        {
            LOG.debug("{} excluded !accept {}",this,request);
            return null;
        }
        boolean gzip = accept.contains("gzip");

        if (!gzip)
        {
            LOG.debug("{} excluded not gzip accept {}",this,request);
            return null;
        }
        
        Deflater df = _deflater.get();
        if (df==null)
            df=new Deflater(_compressionLevel,true);        
        else
            _deflater.set(null);
        
        return df;
    }
