    @Override
    public void resume()
    {
        synchronized (this)
        {
            switch(_state)
            {
                case __HANDLING:
                    _resumed=true;
                    return;

                case __SUSPENDING:
                    _resumed=true;
                    _state=__RESUMING;
                    return;

                case __RESUMING:
                case __COMPLETING:
                    return;

                case __SUSPENDED:
                    fauxResume();
                    _resumed=true;
                    _state=__UNSUSPENDING;
                    break;

                case __UNSUSPENDING:
                    _resumed=true;
                    return;

                default:
                    throw new IllegalStateException(this.getStatusString());
            }
        }

    }
