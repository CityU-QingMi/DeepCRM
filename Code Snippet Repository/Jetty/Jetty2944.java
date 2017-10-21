    private List<String> getPreferredEncodingOrder(HttpServletRequest request)
    {
        Enumeration<String> headers = request.getHeaders(HttpHeader.ACCEPT_ENCODING.asString());
        if (!headers.hasMoreElements())
            return emptyList();

        String key = headers.nextElement();
        if (headers.hasMoreElements())
        {
            StringBuilder sb = new StringBuilder(key.length()*2);
            do
            {
                sb.append(',').append(headers.nextElement());
            } while (headers.hasMoreElements());
            key = sb.toString();
        }

        List<String> values=_preferredEncodingOrderCache.get(key);
        if (values==null)
        {
            QuotedQualityCSV encodingQualityCSV = new QuotedQualityCSV(_preferredEncodingOrder);
            encodingQualityCSV.addValue(key);
            values=encodingQualityCSV.getValues();

            // keep cache size in check even if we get strange/malicious input
            if (_preferredEncodingOrderCache.size()>_encodingCacheSize)
                _preferredEncodingOrderCache.clear();

            _preferredEncodingOrderCache.put(key,values);
        }

        return values;
    }
