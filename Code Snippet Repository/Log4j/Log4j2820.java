    @Setup
    public void setUp() {
        bytes = new byte[128];
        for (int i = 0; i<bytes.length; i++) {
            bytes[i] = (byte)i;
        }

        usAsciiGetBytesLayout = new GetBytesLayout(Charset.forName("US-ASCII"));
        iso8859_1GetBytesLayout = new GetBytesLayout(Charset.forName("ISO-8859-1"));
        utf8GetBytesLayout = new GetBytesLayout(Charset.forName("UTF-8"));
        utf16GetBytesLayout = new GetBytesLayout(Charset.forName("UTF-16"));

        usAsciiEncodeLayout = new EncodeLayout(Charset.forName("US-ASCII"));
        iso8859_1EncodeLayout = new EncodeLayout(Charset.forName("ISO-8859-1"));
        utf8EncodeLayout = new EncodeLayout(Charset.forName("UTF-8"));
        utf16EncodeLayout = new EncodeLayout(Charset.forName("UTF-16"));

        final StringBuilder msg = new StringBuilder();
        msg.append(MESSAGE);

        logEvent = createLogEvent(new SimpleMessage(msg));

        destination = new Destination();
    }
