    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("SimpleServerEndpointMetadata [");
        builder.append("config=").append(config.getClass().getName());
        builder.append(",path=").append(config.getPath());
        builder.append(",endpoint=").append(config.getEndpointClass());
        builder.append(",decoders=").append(config.getDecoders());
        builder.append(",encoders=").append(config.getEncoders());
        builder.append("]");
        return builder.toString();
    }
