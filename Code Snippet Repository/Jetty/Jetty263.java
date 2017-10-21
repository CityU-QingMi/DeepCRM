    @Override
    public String toString()
    {
        return String.format("%s[%s %d %s - %d bytes]",
                HttpContentResponse.class.getSimpleName(),
                getVersion(),
                getStatus(),
                getReason(),
                getContent().length);
    }
