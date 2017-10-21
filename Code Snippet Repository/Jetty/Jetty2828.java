    public void asyncError(Throwable failure)
    {
        AsyncContextEvent event = null;
        try (Locker.Lock lock= _locker.lock())
        {
            switch (_state)
            {
                case IDLE:
                case DISPATCHED:
                case COMPLETING:
                case COMPLETED:
                case UPGRADED:
                case ASYNC_IO:
                case ASYNC_WOKEN:
                case ASYNC_ERROR:
                {
                    break;
                }
                case ASYNC_WAIT:
                {
                    _event.addThrowable(failure);
                    _state=State.ASYNC_ERROR;
                    event=_event;
                    break;
                }
                default:
                {
                    throw new IllegalStateException(getStatusStringLocked());
                }
            }
        }

        if (event != null)
        {
            cancelTimeout(event);
            runInContext(event, _channel);
        }
    }
