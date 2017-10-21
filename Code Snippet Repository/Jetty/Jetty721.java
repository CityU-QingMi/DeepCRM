    @Override
    public void suspend()
    {
        synchronized (this)
        {
            switch(_state)
            {
                case __HANDLING:
                    _timeout=false;
                    _resumed=false;
                    _state=__SUSPENDING;
                    return;

                case __SUSPENDING:
                case __RESUMING:
                    return;

                case __COMPLETING:
                case __SUSPENDED:
                case __UNSUSPENDING:
                    throw new IllegalStateException(this.getStatusString());

                default:
                    throw new IllegalStateException(""+_state);
            }

        }
    }
