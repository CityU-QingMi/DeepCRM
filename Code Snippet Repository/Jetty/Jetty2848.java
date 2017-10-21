    @Override
    public boolean isReady()
    {
        try
        {
            synchronized (_inputQ)
            {
                if (_listener == null)
                    return true;
                if (_state instanceof EOFState)
                    return true;
                if (_waitingForContent)
                    return false;
                if (produceNextContext() != null)
                    return true;
                _channelState.onReadUnready();
                _waitingForContent = true;
            }
            return false;
        }
        catch (IOException e)
        {
            LOG.ignore(e);
            return true;
        }
    }
