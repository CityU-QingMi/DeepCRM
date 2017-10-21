    @Override
    public ContentResponse send() throws InterruptedException, TimeoutException, ExecutionException
    {
        FutureResponseListener listener = new FutureResponseListener(this);
        send(this, listener);

        try
        {
            long timeout = getTimeout();
            if (timeout <= 0)
                return listener.get();

            return listener.get(timeout, TimeUnit.MILLISECONDS);
        }
        catch (Throwable x)
        {
            // Differently from the Future, the semantic of this method is that if
            // the send() is interrupted or times out, we abort the request.
            abort(x);
            throw x;
        }
    }
