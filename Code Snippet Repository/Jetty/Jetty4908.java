    @Override
    public void succeeded()
    {
        boolean process=false;
        try(Locker.Lock lock = _locker.lock())
        {
            switch (_state)
            {
                case PROCESSING:
                {
                    _state=State.CALLED;
                    break;
                }
                case PENDING:
                {
                    _state=State.PROCESSING;
                    process=true;
                    break;
                }
                case CLOSED:
                case FAILED:
                {
                    // Too late!
                    break;
                }
                default:
                {
                    throw new IllegalStateException(toString());
                }
            }
        }
        if (process)
            processing();
    }
