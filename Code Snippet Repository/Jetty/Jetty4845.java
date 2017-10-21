    @Override
    public void succeeded()
    {
        while (true)
        {
            State current = state.get();
            switch (current)
            {
                case IDLE:
                {
                    if (state.compareAndSet(current, State.SUCCEEDED))
                        return;
                    break;
                }
                case COMPLETED:
                {
                    if (state.compareAndSet(current, State.SUCCEEDED))
                    {
                        resume();
                        return;
                    }
                    break;
                }
                case FAILED:
                {
                    return;
                }
                default:
                {
                    throw new IllegalStateException(current.toString());
                }
            }
        }
    }
