    void disassociate(HttpChannel channel)
    {
        boolean abort = false;
        synchronized (this)
        {
            if (_channel != channel || requestState != State.TERMINATED || responseState != State.TERMINATED)
                abort = true;
            _channel = null;
        }

        if (abort)
            request.abort(new IllegalStateException(toString()));
    }
