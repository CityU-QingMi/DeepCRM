    @Override
    public void send(Request request, Response.CompleteListener listener)
    {
        HttpRequest httpRequest = (HttpRequest)request;

        ArrayList<Response.ResponseListener> listeners = new ArrayList<>(httpRequest.getResponseListeners());
        if (httpRequest.getTimeout() > 0)
        {
            TimeoutCompleteListener timeoutListener = new TimeoutCompleteListener(httpRequest);
            timeoutListener.schedule(getHttpClient().getScheduler());
            listeners.add(timeoutListener);
        }
        if (listener != null)
            listeners.add(listener);

        HttpExchange exchange = new HttpExchange(getHttpDestination(), httpRequest, listeners);

        SendFailure result = send(exchange);
        if (result != null)
            httpRequest.abort(result.failure);
    }
