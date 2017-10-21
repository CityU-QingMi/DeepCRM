    @Override
    public boolean abort(Throwable cause)
    {
        if (aborted.compareAndSet(null, Objects.requireNonNull(cause)))
        {
            if (content instanceof Callback)
                ((Callback)content).failed(cause);
            return conversation.abort(cause);
        }
        return false;
    }
