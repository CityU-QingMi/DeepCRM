    public void encode(ByteBuffer buffer, MetaData metadata)
    {
        if (LOG.isDebugEnabled())
            LOG.debug(String.format("CtxTbl[%x] encoding",_context.hashCode()));

        _headerListSize=0;
        int pos = buffer.position();

        // Check the dynamic table sizes!
        int maxDynamicTableSize=Math.min(_remoteMaxDynamicTableSize,_localMaxDynamicTableSize);
        if (maxDynamicTableSize!=_context.getMaxDynamicTableSize())
            encodeMaxDynamicTableSize(buffer,maxDynamicTableSize);

        // Add Request/response meta fields
        if (metadata.isRequest())
        {
            MetaData.Request request = (MetaData.Request)metadata;

            // TODO optimise these to avoid HttpField creation
            String scheme=request.getURI().getScheme();
            encode(buffer,new HttpField(HttpHeader.C_SCHEME,scheme==null?HttpScheme.HTTP.asString():scheme));
            encode(buffer,new HttpField(HttpHeader.C_METHOD,request.getMethod()));
            encode(buffer,new HttpField(HttpHeader.C_AUTHORITY,request.getURI().getAuthority()));
            encode(buffer,new HttpField(HttpHeader.C_PATH,request.getURI().getPathQuery()));
        }
        else if (metadata.isResponse())
        {
            MetaData.Response response = (MetaData.Response)metadata;
            int code=response.getStatus();
            HttpField status = code<__status.length?__status[code]:null;
            if (status==null)
                status=new HttpField.IntValueHttpField(HttpHeader.C_STATUS,code);
            encode(buffer,status);
        }

        // Add all the other fields
        for (HttpField field : metadata)
            encode(buffer,field);

        // Check size
        if (_maxHeaderListSize>0 && _headerListSize>_maxHeaderListSize)
        {
            LOG.warn("Header list size too large {} > {} for {}",_headerListSize,_maxHeaderListSize);
            if (LOG.isDebugEnabled())
                LOG.debug("metadata={}",metadata);
        }
        
        if (LOG.isDebugEnabled())
            LOG.debug(String.format("CtxTbl[%x] encoded %d octets",_context.hashCode(), buffer.position() - pos));
    }
