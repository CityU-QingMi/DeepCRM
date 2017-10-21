    public static void parseAll(HttpParser parser, ByteBuffer buffer)
    {
        if (parser.isState(State.END))
            parser.reset();
        if (!parser.isState(State.START))
            throw new IllegalStateException("!START");

        // continue parsing
        int remaining = buffer.remaining();
        while (!parser.isState(State.END) && remaining > 0)
        {
            int was_remaining = remaining;
            parser.parseNext(buffer);
            remaining = buffer.remaining();
            if (remaining == was_remaining)
                break;
        }
    }
