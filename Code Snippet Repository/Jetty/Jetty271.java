    @Override
    public String toString()
    {
        return String.format("%s[%s]@%x%s,queue=%d,pool=%s",
                HttpDestination.class.getSimpleName(),
                asString(),
                hashCode(),
                proxy == null ? "" : "(via " + proxy + ")",
                exchanges.size(),
                connectionPool);
    }
