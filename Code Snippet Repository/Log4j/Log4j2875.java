    @Override
    public void append(final LogEvent event) {
        final Layout<?> layout = getLayout();
        if (layout instanceof NoGcLayout) {
            layout.encode(event, this);
            drain(byteBuffer);
        } else {
            final byte[] binary = getLayout().toByteArray(event);
            consume(binary, 0, binary.length);
        }
    }
