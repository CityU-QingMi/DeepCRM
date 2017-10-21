    @Override
    public String toConnectionString()
    {
        return String.format("%s@%x[p=%s,g=%s]=>%s",
                getClass().getSimpleName(),
                hashCode(),
                _parser,
                _generator,
                _channel);
    }
