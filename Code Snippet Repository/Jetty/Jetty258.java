    @Override
    public void succeeded()
    {
        if (isConsumed())
            return;
        if (buffer == CLOSE)
            return;
        if (iterator instanceof Callback)
            ((Callback)iterator).succeeded();
    }
