    @Override
    public String toString()
    {
        return String.format("%s@%x{r=%s,c=%b,a=%s,uri=%s}",
                getClass().getSimpleName(),
                hashCode(),
                _requests,
                _committed.get(),
                _state.getState(),
                _request.getHttpURI());
    }
