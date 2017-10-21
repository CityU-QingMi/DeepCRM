    boolean associate(HttpChannel channel)
    {
        boolean result = false;
        boolean abort = false;
        synchronized (this)
        {
            // Only associate if the exchange state is initial,
            // as the exchange could be already failed.
            if (requestState == State.PENDING && responseState == State.PENDING)
            {
                abort = _channel != null;
                if (!abort)
                {
                    _channel = channel;
                    result = true;
                }
            }
        }

        if (abort)
            request.abort(new IllegalStateException(toString()));

        return result;
    }
