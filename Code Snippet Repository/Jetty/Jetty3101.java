    @Override
    public final void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        if (isStarted())
        {
            if (_outerScope==null)
                doScope(target,baseRequest,request, response);
            else
                doHandle(target,baseRequest,request, response);
        }
    }
