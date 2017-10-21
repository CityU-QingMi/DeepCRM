    @Override
    public String toString()
    {
        return String.format("%s@%x(req=%s,snd=%s,failure=%s)",
                getClass().getSimpleName(),
                hashCode(),
                requestState,
                senderState,
                failure);
    }
