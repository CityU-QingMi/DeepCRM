    @Override
    public void close(int statusCode, String message)
    {
        LOG.debug("close({},{})",statusCode,message);
        CloseInfo close = new CloseInfo(statusCode,message);

        if (!ioState.isClosed())
        {
            ioState.onCloseLocal(close);
        }
        else
        {
            LOG.debug("Not issuing close. ioState = {}",ioState);
        }
    }
