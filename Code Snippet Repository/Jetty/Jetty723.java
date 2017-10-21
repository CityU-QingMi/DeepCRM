    @Override
    public void complete()
    {
        // just like resume, except don't set _resumed=true;
        synchronized (this)
        {
            switch(_state)
            {
                case __HANDLING:
                    throw new IllegalStateException(this.getStatusString());

                case __SUSPENDING:
                    _state=__COMPLETING;
                    break;

                case __RESUMING:
                    break;

                case __COMPLETING:
                    return;

                case __SUSPENDED:
                    _state=__COMPLETING;
                    fauxResume();
                    break;

                case __UNSUSPENDING:
                    return;

                default:
                    throw new IllegalStateException(this.getStatusString());
            }
        }
    }
