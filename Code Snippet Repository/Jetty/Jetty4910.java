    public void close()
    {
        boolean failure=false;
        try(Locker.Lock lock = _locker.lock())
        {
            switch (_state)
            {
                case IDLE:
                case SUCCEEDED:
                case FAILED:
                    _state=State.CLOSED;
                    break;

                case CLOSED:
                    break;

                default:
                    _state=State.CLOSED;
                    failure=true;
            }
        }

        if(failure)
            onCompleteFailure(new ClosedChannelException());
    }
