    @Override
    public boolean isSuspended()
    {
        synchronized(this)
        {
            switch(_state)
            {
                case __HANDLING:
                    return false;
                case __SUSPENDING:
                case __RESUMING:
                case __COMPLETING:
                case __SUSPENDED:
                    return true;
                case __UNSUSPENDING:
                default:
                    return false;
            }
        }
    }
