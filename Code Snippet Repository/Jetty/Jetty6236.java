    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("AnnotatedServerEndpointMetadata[endpoint=");
        builder.append(endpoint);
        builder.append(",config=");
        builder.append(config);
        builder.append("]");
        return builder.toString();
    }
