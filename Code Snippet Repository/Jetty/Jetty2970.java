    private void setError(Throwable th)
    {
        super.setError();

        if (th instanceof IOException)
            _ioException=(IOException)th;
        else
        {
            _ioException=new IOException(String.valueOf(th));
            _ioException.initCause(th);
        }

        if (LOG.isDebugEnabled())
            LOG.debug(th);
    }
