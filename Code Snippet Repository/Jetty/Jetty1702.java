    private boolean updateState(State previous,State next)
    {
        if (!isTransitionAllowed(previous,next))
            throw new IllegalStateException();

        boolean updated = _state.compareAndSet(previous, next);
        if (DEBUG)
            LOG.debug("update {}:{}{}{}", this, previous, updated?"-->":"!->",next);
        return updated;
    }
