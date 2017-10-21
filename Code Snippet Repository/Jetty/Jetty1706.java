    private boolean isTransitionAllowed(State currentState, State newState)
    {
        Set<StateType> allowedNewStateTypes = __stateTransitions.get(currentState.getType());
        if (!allowedNewStateTypes.contains(newState.getType()))
        {
            LOG.warn("{}: {} -> {} not allowed", this, currentState, newState);
            return false;
        }
        return true;
    }
