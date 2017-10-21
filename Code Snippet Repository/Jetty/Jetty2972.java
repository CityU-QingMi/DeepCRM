    @Override
    public PrintWriter format(Locale l, String format, Object... args)
    { 
        try 
        {
            synchronized (lock) 
            {
                isOpen();
                if ((_formatter == null) || (_formatter.locale() != l))
                    _formatter = new Formatter(this, l);
                _formatter.format(l, format, args);
            }
        } 
        catch (InterruptedIOException ex)
        {
            LOG.debug(ex);
            Thread.currentThread().interrupt();
        }
        catch (IOException ex)
        {
            setError(ex);
        }
        return this;
    }
