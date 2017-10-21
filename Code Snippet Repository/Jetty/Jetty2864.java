    @Override
    public boolean isReady()
    {
        while (true)
        {
            switch (_state.get())
            {
                case OPEN:
                    return true;

                case ASYNC:
                    if (!_state.compareAndSet(OutputState.ASYNC, OutputState.READY))
                        continue;
                    return true;

                case READY:
                    return true;

                case PENDING:
                    if (!_state.compareAndSet(OutputState.PENDING, OutputState.UNREADY))
                        continue;
                    return false;

                case UNREADY:
                    return false;

                case ERROR:
                    return true;

                case CLOSED:
                    return true;

                default:
                    throw new IllegalStateException();
            }
        }
    }
