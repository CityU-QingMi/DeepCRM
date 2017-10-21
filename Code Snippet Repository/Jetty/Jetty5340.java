    @Override
    public void dispatch()
    {
        boolean execute = false;
        try (Lock locked = _locker.lock())
        {
            switch(_state)
            {
                case IDLE:
                    execute = true;
                    break;
                    
                case PRODUCING:
                    _state = State.REPRODUCING;
                    break;
                    
                default:
                    break;
            }
        }
        if (LOG.isDebugEnabled())
            LOG.debug("{} dispatch {}", this, execute);
        if (execute)
            _executor.execute(this);
    }
