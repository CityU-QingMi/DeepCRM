    protected SendFailure send(HttpChannel channel, HttpExchange exchange)
    {
        // Forbid idle timeouts for the time window where
        // the request is associated to the channel and sent.
        // Use a counter to support multiplexed requests.
        boolean send;
        synchronized (this)
        {
            send = idleTimeoutGuard >= 0;
            if (send)
                ++idleTimeoutGuard;
        }

        if (send)
        {
            HttpRequest request = exchange.getRequest();
            SendFailure result;
            if (channel.associate(exchange))
            {
                channel.send();
                result = null;
            }
            else
            {
                channel.release();
                result = new SendFailure(new HttpRequestException("Could not associate request to connection", request), false);
            }

            synchronized (this)
            {
                --idleTimeoutGuard;
                idleTimeoutStamp = System.nanoTime();
            }

            return result;
        }
        else
        {
            return new SendFailure(new TimeoutException(), true);
        }
    }
