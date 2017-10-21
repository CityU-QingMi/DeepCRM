    @Override
    public void failed(Throwable x)
    {
        if (isConsumed())
            return;
        if (buffer == CLOSE)
            return;
        if (iterator instanceof Callback)
            ((Callback)iterator).failed(x);
    }
