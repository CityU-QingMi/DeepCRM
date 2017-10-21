    public static SettingsFrame parseBody(final ByteBuffer buffer)
    {
        final int bodyLength = buffer.remaining();
        final AtomicReference<SettingsFrame> frameRef = new AtomicReference<>();
        SettingsBodyParser parser = new SettingsBodyParser(null, null)
        {
            @Override
            protected int getStreamId()
            {
                return 0;
            }

            @Override
            protected int getBodyLength()
            {
                return bodyLength;
            }

            @Override
            protected boolean onSettings(Map<Integer, Integer> settings)
            {
                frameRef.set(new SettingsFrame(settings, false));
                return true;
            }

            @Override
            protected boolean connectionFailure(ByteBuffer buffer, int error, String reason)
            {
                frameRef.set(null);
                return false;
            }
        };
        if (bodyLength == 0)
            parser.emptyBody(buffer);
        else
            parser.parse(buffer);
        return frameRef.get();
    }
