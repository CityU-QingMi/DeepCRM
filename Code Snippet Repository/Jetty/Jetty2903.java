    public PushBuilderImpl(Request request, HttpFields fields, String method, String queryString, String sessionId, boolean conditional)
    {
        super();
        _request = request;
        _fields = fields;
        _method = method;
        _queryString = queryString;
        _sessionId = sessionId;
        _conditional = conditional;
        _fields.add(JettyPush);
        if (LOG.isDebugEnabled())
            LOG.debug("PushBuilder({} {}?{} s={} c={})",_method,_request.getRequestURI(),_queryString,_sessionId,_conditional);
    }
