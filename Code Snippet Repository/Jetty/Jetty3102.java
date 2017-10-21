    public final void nextScope(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException
    {
        if (_nextScope!=null)
            _nextScope.doScope(target,baseRequest,request, response);
        else if (_outerScope!=null)
            _outerScope.doHandle(target,baseRequest,request, response);
        else
            doHandle(target,baseRequest,request, response);
    }
