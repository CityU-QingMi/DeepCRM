    private boolean parseByte(byte b)
    {
        switch (state)
        {
            case START:
                utf = new Utf8StringBuilder();
                state = State.PARSE;
                return parseByte(b);
            case PARSE:
                // not waiting on more UTF sequence parts.
                if (utf.isUtf8SequenceComplete() && ((b == '\r') || (b == '\n')))
                {
                    state = State.END;
                    return parseByte(b);
                }
                utf.append(b);
                break;
            case END:
                if (b == '\n')
                {
                    // we've reached the end
                    state = State.START;
                    return true;
                }
                break;
        }
        return false;
    }
