    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("AnnotatedServerEndpointConfig[endpointClass=");
        builder.append(endpointClass);
        builder.append(",path=");
        builder.append(path);
        builder.append(",decoders=");
        builder.append(decoders);
        builder.append(",encoders=");
        builder.append(encoders);
        builder.append(",subprotocols=");
        builder.append(subprotocols);
        builder.append(",extensions=");
        builder.append(extensions);
        builder.append("]");
        return builder.toString();
    }
