    @Override
    public boolean startRequest(String method, String uri, HttpVersion version)
    {
        _metadata.setMethod(method);
        _metadata.getURI().parseRequestTarget(method, uri);
        _metadata.setHttpVersion(version);
        _unknownExpectation = false;
        _expect100Continue = false;
        _expect102Processing = false;
        return false;
    }
