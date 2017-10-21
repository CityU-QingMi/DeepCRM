    @Override
    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException
    {
        Handler[] handlers = getHandlers();

        if (handlers!=null && isStarted())
        {
            for (int i=0;i<handlers.length;i++)
            {
                handlers[i].handle(target,baseRequest, request, response);
                if ( baseRequest.isHandled())
                    return;
            }
        }
    }
