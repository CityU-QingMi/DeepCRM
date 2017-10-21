    @Override
    public void failed(Throwable x)
    {
        boolean failure=false;
        try(Locker.Lock lock = _locker.lock())
        {
            switch (_state)
            {
                case SUCCEEDED:
                case FAILED:
                case IDLE:
                case CLOSED:
                case CALLED:
                    // too late!.
                    break;

                case PENDING:
                case PROCESSING:
                {
                    _state=State.FAILED;
                    failure=true;
                    break;
                }
                default:
                    throw new IllegalStateException(toString());
            }
        }
        if (failure)
            onCompleteFailure(x);
    }
