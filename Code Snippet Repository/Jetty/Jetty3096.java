    @Override
    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        if (baseRequest.isHandled())
            return;

        if (!HttpMethod.GET.is(request.getMethod()))
        {
            if (!HttpMethod.HEAD.is(request.getMethod()))
            {
                // try another handler
                super.handle(target,baseRequest,request,response);
                return;
            }
        }

        _resourceService.doGet(request,response);

        if (response.isCommitted())
            baseRequest.setHandled(true);
        else
            // no resource - try other handlers
            super.handle(target,baseRequest,request,response);
    }
