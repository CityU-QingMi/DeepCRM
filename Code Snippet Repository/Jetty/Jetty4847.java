    public boolean tryComplete()
    {
        while (true)
        {
            State current = state.get();
            switch (current)
            {
                case IDLE:
                {
                    if (state.compareAndSet(current, State.COMPLETED))
                        return true;
                    break;
                }
                case SUCCEEDED:
                case FAILED:
                {
                    return false;
                }
                default:
                {
                    throw new IllegalStateException(current.toString());
                }
            }
        }
    }
