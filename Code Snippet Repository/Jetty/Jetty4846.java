    @Override
    public void failed(Throwable x)
    {
        while (true)
        {
            State current = state.get();
            switch (current)
            {
                case IDLE:
                case COMPLETED:
                {
                    if (state.compareAndSet(current, State.FAILED))
                    {
                        abort(x);
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
