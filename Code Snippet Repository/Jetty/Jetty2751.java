    @Override
    public void addListener(final AsyncListener listener, final ServletRequest request, final ServletResponse response)
    {
        AsyncListener wrap = new AsyncListener()
        {
            @Override
            public void onTimeout(AsyncEvent event) throws IOException
            {
                listener.onTimeout(new AsyncEvent(event.getAsyncContext(),request,response,event.getThrowable()));
            }
            
            @Override
            public void onStartAsync(AsyncEvent event) throws IOException
            {
                listener.onStartAsync(new AsyncEvent(event.getAsyncContext(),request,response,event.getThrowable()));
            }
            
            @Override
            public void onError(AsyncEvent event) throws IOException
            {
                listener.onError(new AsyncEvent(event.getAsyncContext(),request,response,event.getThrowable()));
            }
            
            @Override
            public void onComplete(AsyncEvent event) throws IOException
            {                
                listener.onComplete(new AsyncEvent(event.getAsyncContext(),request,response,event.getThrowable()));
            }
        };
        state().addListener(wrap);
    }
