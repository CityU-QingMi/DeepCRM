    @Override
    public void earlyEOF()
    {
        _httpConnection.getGenerator().setPersistent(false);
        // If we have no request yet, just close
        if (_metadata.getMethod() == null)
            _httpConnection.close();
        else if (onEarlyEOF() || _delayedForContent)
        { 
            _delayedForContent = false;
            handle();
        }
    }
