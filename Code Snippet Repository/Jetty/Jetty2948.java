    protected void putHeaders(HttpServletResponse response,HttpContent content, long contentLength)
    {
        if (response instanceof Response)
        {
            Response r = (Response)response;
            r.putHeaders(content,contentLength,_etags);
            HttpFields f = r.getHttpFields();
            if (_acceptRanges)
                f.put(ACCEPT_RANGES);

            if (_cacheControl!=null)
                f.put(_cacheControl);
        }
        else
        {
            Response.putHeaders(response,content,contentLength,_etags);
            if (_acceptRanges)
                response.setHeader(ACCEPT_RANGES.getName(),ACCEPT_RANGES.getValue());

            if (_cacheControl!=null)
                response.setHeader(_cacheControl.getName(),_cacheControl.getValue());
        }
    }
