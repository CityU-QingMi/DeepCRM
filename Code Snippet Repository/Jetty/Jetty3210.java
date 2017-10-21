    protected void finishInvalidate() throws IllegalStateException
    {
        try (Lock lock = _lock.lock())
        {
            try
            {
                if (LOG.isDebugEnabled())
                    LOG.debug("invalidate {}",_sessionData.getId());
                if (_state == State.VALID || _state == State.INVALIDATING)
                {
                    Set<String> keys = null;
                    do
                    {
                        keys = _sessionData.getKeys();
                        for (String key:keys)
                        {
                            Object  old=_sessionData.setAttribute(key,null);
                            if (old == null)
                                return; //if same as remove attribute but attribute was already removed, no change
                            callSessionAttributeListeners(key, null, old);
                        }

                    }
                    while (!keys.isEmpty());
                }
            }
            finally
            {
                // mark as invalid
                _state = State.INVALID;
            }
        }
    }
