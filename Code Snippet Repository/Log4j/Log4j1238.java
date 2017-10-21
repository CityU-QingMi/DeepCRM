    @Override
    public void encode(final StringBuilder source, final ByteBufferDestination destination) {
        try {
            // This synchronized is needed to be able to call destination.getByteBuffer()
            synchronized (destination) {
                TextEncoderHelper.encodeText(charsetEncoder, cachedCharBuffer, destination.getByteBuffer(), source,
                    destination);
            }
        } catch (final Exception ex) {
            logEncodeTextException(ex, source, destination);
            TextEncoderHelper.encodeTextFallBack(charset, source, destination);
        }

    }
