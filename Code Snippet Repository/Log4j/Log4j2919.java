    @Override
    public void append(final LogEvent event) {
        if (Constants.ENABLE_DIRECT_ENCODERS) {
            getLayout().encode(event, this);
            drain(byteBuffer);
        } else {
            final byte[] binary = getLayout().toByteArray(event);
            consume(binary, 0, binary.length);
        }
    }
