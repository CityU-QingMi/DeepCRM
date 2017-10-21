    private void fail(PendingState pending)
    {
        State current = _state.get();
        if (current.getType()==StateType.FAILED)
        {
            FailedState failed=(FailedState)current;
            if (updateState(failed,__IDLE))
            {
                pending.fail(failed.getCause());
                return;
            }
        }
        throw new IllegalStateException();
    }
