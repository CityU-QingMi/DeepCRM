    @Override
    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        if (isStarted())
        {
            if (_dispatchTypes.contains(baseRequest.getDispatcherType()))
            {
                String returned = _rules.matchAndApply(target, request, response);
                target = (returned == null) ? target : returned;
            }

            if (!baseRequest.isHandled())
                super.handle(target, baseRequest, request, response);
        }
    }
