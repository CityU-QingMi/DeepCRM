    public MetaData build()
    {
        try
        {
            HttpFields fields = _fields;
            _fields = new HttpFields(Math.max(10,fields.size()+5));

            if (_method!=null)
                return new MetaData.Request(_method,_scheme,_authority,_path,HttpVersion.HTTP_2,fields,_contentLength);
            if (_status!=0)
                return new MetaData.Response(HttpVersion.HTTP_2,_status,fields,_contentLength);
            return new MetaData(HttpVersion.HTTP_2,fields,_contentLength);
        }
        finally
        {
            _status=0;
            _method=null;
            _scheme=null;
            _authority=null;
            _path=null;
            _size=0;
            _contentLength=Long.MIN_VALUE;
        }
    }
