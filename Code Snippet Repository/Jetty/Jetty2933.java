    @Override
    public String toString()
    {
        return String.format("%s%s%s %s%s@%x",
                getClass().getSimpleName(),
                _handled ? "[" : "(",
                getMethod(),
                getHttpURI(),
                _handled ? "]" : ")",
                hashCode());
    }
