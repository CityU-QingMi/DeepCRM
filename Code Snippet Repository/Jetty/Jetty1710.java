    public boolean onFail(Throwable cause)
    {
        // Keep trying to handle the failure until we get to IDLE or FAILED state
        while(true)
        {
            State current=_state.get();
            switch(current.getType())
            {
                case IDLE:
                case FAILED:
                    if (DEBUG)
                        LOG.debug("ignored: {} {}", this, cause);
                    return false;

                case PENDING:
                    if (DEBUG)
                        LOG.debug("failed: {} {}", this, cause);

                    PendingState pending = (PendingState)current;
                    if (updateState(pending,__IDLE))
                        return pending.fail(cause);
                    break;

                default:
                    if (DEBUG)
                        LOG.debug("failed: {} {}", this, cause);

                    if (updateState(current,new FailedState(cause)))
                        return false;
                    break;
            }
        }
    }
