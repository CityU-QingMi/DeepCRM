    @Override
    public String toString()
    {
        return String.format("%s@%x(%s,h=%s,w=%s)",
                getClass().getSimpleName(),
                hashCode(),
                _alias,
                _hosts,
                _wilds);
    }
