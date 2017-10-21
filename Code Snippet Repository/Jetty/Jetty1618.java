    private void doOnClose(Throwable failure)
    {
        try
        {
            doClose();
        }
        finally
        {
            if (failure == null)
                onClose();
            else
                onClose(failure);
        }
    }
