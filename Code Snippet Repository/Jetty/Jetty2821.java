    public String toStringLocked()
    {
        return String.format("%s@%x{s=%s a=%s i=%b r=%s w=%b}",
            getClass().getSimpleName(),
            hashCode(),
            _state,
            _async,
            _initial,
            _asyncRead,
            _asyncWritePossible);
    }
