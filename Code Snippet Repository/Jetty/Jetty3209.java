    protected boolean beginInvalidate()
    {
        boolean result = false;
        
        try (Lock lock = _lock.lock())
        {
            switch (_state)
            {
                case INVALID:
                {
                    throw new IllegalStateException(); //spec does not allow invalidate of already invalid session
                }
                case VALID:
                {
                    //only first change from valid to invalidating should be actionable
                    result = true;
                    _state = State.INVALIDATING;
                    break;
                }
                default:
                {
                    if (LOG.isDebugEnabled()) LOG.debug("Session {} already being invalidated", _sessionData.getId());
                }
            }
        }
        
        return result;
    }
