    void handling()
    {
        synchronized (this)
        {
            _responseWrapped=false;
            switch(_state)
            {
                case __HANDLING:
                    throw new IllegalStateException(this.getStatusString());

                case __SUSPENDING:
                case __RESUMING:
                    throw new IllegalStateException(this.getStatusString());

                case __COMPLETING:
                    return;

                case __SUSPENDED:
                    fauxResume();
                    _state=__HANDLING;
                    return;
                    
                case __UNSUSPENDING:
                    _state=__HANDLING;
                    return;

                default:
                    throw new IllegalStateException(""+_state);
            }

        }
    }
