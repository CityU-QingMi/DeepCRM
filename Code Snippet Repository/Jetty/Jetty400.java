    @Override
    public String toConnectionString()
    {
        return String.format("%s@%x(l:%s <-> r:%s,closed=%b)=>%s",
            getClass().getSimpleName(),
            hashCode(),
            getEndPoint().getLocalAddress(),
            getEndPoint().getRemoteAddress(),
            closed.get(),
            channel);
    }
