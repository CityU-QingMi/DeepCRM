        @Override
        public void doNonErrorHandle(String target, Request baseRequest, final HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
        {
            if (request.getAttribute(CONTEXT_ATTRIBUTE) == null)
            {
                final AsyncContext asyncContext = baseRequest.startAsync();
                request.setAttribute(CONTEXT_ATTRIBUTE, asyncContext);
                new Thread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        if (dispatch)
                            asyncContext.dispatch();
                        else
                            asyncContext.complete();
                    }
                }).run();
            }
            baseRequest.setHandled(true);
            super.doNonErrorHandle(target, baseRequest, request, response);
        }
