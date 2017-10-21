    @Override
    public boolean exit()
    {
        synchronized (this)
        {
            switch(_state)
            {
                case __HANDLING:
                    _state=__COMPLETE;
                    onComplete();
                    return true;

                case __SUSPENDING:
                    _initial=false;
                    _state=__SUSPENDED;
                    fauxSuspend(); // could block and change state.
                    if (_state==__SUSPENDED || _state==__COMPLETING)
                    {
                        onComplete();
                        return true;
                    }

                    _initial=false;
                    _state=__HANDLING;
                    return false;

                case __RESUMING:
                    _initial=false;
                    _state=__HANDLING;
                    return false;

                case __COMPLETING:
                    _initial=false;
                    _state=__COMPLETE;
                    onComplete();
                    return true;

                case __SUSPENDED:
                case __UNSUSPENDING:
                default:
                    throw new IllegalStateException(this.getStatusString());
            }
        }
    }
