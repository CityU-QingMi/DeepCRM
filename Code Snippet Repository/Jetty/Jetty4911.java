    public boolean reset()
    {
        try(Locker.Lock lock = _locker.lock())
        {
            switch(_state)
            {
                case IDLE:
                    return true;

                case SUCCEEDED:
                case FAILED:
                    _iterate=false;
                    _state=State.IDLE;
                    return true;

                default:
                    return false;
            }
        }
    }
