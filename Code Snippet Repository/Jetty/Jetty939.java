    @Override
    public void sendResponseHeaders(int rCode, long responseLength) throws IOException
    {
        this._responseCode = rCode;

        for (Map.Entry<String, List<String>> stringListEntry : _responseHeaders.entrySet())
        {
            String name = stringListEntry.getKey();
            List<String> values = stringListEntry.getValue();

            for (String value : values)
            {
                _resp.setHeader(name,value);
            }
        }
        if (responseLength > 0)
        {
            _resp.setHeader("content-length","" + responseLength);
        }
        _resp.setStatus(rCode);
    }
