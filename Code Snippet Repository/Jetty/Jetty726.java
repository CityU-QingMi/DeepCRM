    protected void expire()
    {
        // just like resume, except don't set _resumed=true;

        synchronized (this)
        {
            _timeout=true;
        }

        onTimeout();

        synchronized (this)
        {
            switch(_state)
            {
                case __HANDLING:
                    return;

                case __SUSPENDING:
                    _timeout=true;
                    _state=__RESUMING;
                    fauxResume();
                    return;

                case __RESUMING:
                    return;

                case __COMPLETING:
                    return;

                case __SUSPENDED:
                    _timeout=true;
                    _state=__UNSUSPENDING;
                    break;

                case __UNSUSPENDING:
                    _timeout=true;
                    return;

                default:
                    throw new IllegalStateException(this.getStatusString());
            }
        }
    }
