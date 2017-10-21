    @Override
    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        final HttpChannel channel = baseRequest.getHttpChannel();
        final long idle_timeout=baseRequest.getHttpChannel().getIdleTimeout();
        channel.setIdleTimeout(_idleTimeoutMs);
        
        try
        {
            super.handle(target,baseRequest,request,response);
        }
        finally
        {
            if (_applyToAsync && request.isAsyncStarted())
            {
                request.getAsyncContext().addListener(new AsyncListener()
                {
                    @Override
                    public void onTimeout(AsyncEvent event) throws IOException
                    {                            
                    }

                    @Override
                    public void onStartAsync(AsyncEvent event) throws IOException
                    {
                    }

                    @Override
                    public void onError(AsyncEvent event) throws IOException
                    {
                        channel.setIdleTimeout(idle_timeout);
                    }

                    @Override
                    public void onComplete(AsyncEvent event) throws IOException
                    {
                        channel.setIdleTimeout(idle_timeout);
                    }
                });
            }
            else 
                channel.setIdleTimeout(idle_timeout);
        }
    }
