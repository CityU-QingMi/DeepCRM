    public boolean onStreamTimeout(Throwable failure, Consumer<Runnable> consumer)
    {
        boolean result = false;
        if (isRequestIdle())
        {
            consumeInput();
            result = true;
        }

        getHttpTransport().onStreamTimeout(failure);
        if (getRequest().getHttpInput().onIdleTimeout(failure))
            consumer.accept(this::handleWithContext);

        return result;
    }
