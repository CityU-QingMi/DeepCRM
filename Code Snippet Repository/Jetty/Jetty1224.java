    public boolean upgrade(Request request)
    {
        if (HttpMethod.PRI.is(request.getMethod()))
        {
            getParser().directUpgrade();
        }
        else
        {
            HttpField settingsField = request.getFields().getField(HttpHeader.HTTP2_SETTINGS);
            if (settingsField == null)
                throw new BadMessageException("Missing " + HttpHeader.HTTP2_SETTINGS + " header");
            String value = settingsField.getValue();
            final byte[] settings = B64Code.decodeRFC4648URL(value == null ? "" : value);

            if (LOG.isDebugEnabled())
                LOG.debug("{} settings {}",this,TypeUtil.toHexString(settings));

            SettingsFrame settingsFrame = SettingsBodyParser.parseBody(BufferUtil.toBuffer(settings));
            if (settingsFrame == null)
            {
                LOG.warn("Invalid {} header value: {}", HttpHeader.HTTP2_SETTINGS, value);
                throw new BadMessageException();
            }

            getParser().standardUpgrade();

            upgradeFrames.add(new PrefaceFrame());
            upgradeFrames.add(settingsFrame);
            // Remember the request to send a response from onOpen().
            upgradeFrames.add(new HeadersFrame(1, new Request(request), null, true));
        }
        return true;
    }
