    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("WebSocketPolicy@").append(Integer.toHexString(hashCode()));
        builder.append("[behavior=").append(behavior);
        builder.append(",maxTextMessageSize=").append(maxTextMessageSize);
        builder.append(",maxTextMessageBufferSize=").append(maxTextMessageBufferSize);
        builder.append(",maxBinaryMessageSize=").append(maxBinaryMessageSize);
        builder.append(",maxBinaryMessageBufferSize=").append(maxBinaryMessageBufferSize);
        builder.append(",asyncWriteTimeout=").append(asyncWriteTimeout);
        builder.append(",idleTimeout=").append(idleTimeout);
        builder.append(",inputBufferSize=").append(inputBufferSize);
        builder.append("]");
        return builder.toString();
    }
