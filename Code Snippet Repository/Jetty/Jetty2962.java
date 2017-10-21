    public static void putHeaders(HttpServletResponse response, HttpContent content, long contentLength, boolean etag)
    {
        long lml=content.getResource().lastModified();
        if (lml>=0)
            response.setDateHeader(HttpHeader.LAST_MODIFIED.asString(),lml);

        if (contentLength==0)
            contentLength=content.getContentLengthValue();
        if (contentLength >=0)
        {
            if (contentLength<Integer.MAX_VALUE)
                response.setContentLength((int)contentLength);
            else
                response.setHeader(HttpHeader.CONTENT_LENGTH.asString(),Long.toString(contentLength));
        }

        String ct=content.getContentTypeValue();
        if (ct!=null && response.getContentType()==null)
            response.setContentType(ct);

        String ce=content.getContentEncodingValue();
        if (ce!=null)
            response.setHeader(HttpHeader.CONTENT_ENCODING.asString(),ce);

        if (etag)
        {
            String et=content.getETagValue();
            if (et!=null)
                response.setHeader(HttpHeader.ETAG.asString(),et);
        }
    }
