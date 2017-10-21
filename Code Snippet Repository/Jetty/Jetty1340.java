    public void decodeQueryTo(MultiMap<String> parameters, Charset encoding) throws UnsupportedEncodingException
    {
        if (_query==_fragment)
            return;

        if (encoding==null || StandardCharsets.UTF_8.equals(encoding))
            UrlEncoded.decodeUtf8To(_query,parameters);
        else
            UrlEncoded.decodeTo(_query,parameters,encoding);
    }
