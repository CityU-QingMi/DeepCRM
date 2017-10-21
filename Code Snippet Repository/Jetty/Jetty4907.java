    public void iterate()
    {
        boolean process=false;

        loop: while (true)
        {
            try (Locker.Lock lock = _locker.lock())
            {
                switch (_state)
                {
                    case PENDING:
                    case CALLED:
                        // process will be called when callback is handled
                        break loop;

                    case IDLE:
                        _state=State.PROCESSING;
                        process=true;
                        break loop;

                    case PROCESSING:
                        _iterate=true;
                        break loop;

                    case FAILED:
                    case SUCCEEDED:
                        break loop;

                    case CLOSED:
                    default:
                        throw new IllegalStateException(toString());
                }
            }
        }
        if (process)
            processing();
    }
