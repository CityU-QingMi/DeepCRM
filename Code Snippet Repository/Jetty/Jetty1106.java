    @Override
    public boolean parse(ByteBuffer buffer)
    {
        while (buffer.hasRemaining())
        {
            switch (state)
            {
                case PREPARE:
                {
                    // SPEC: wrong streamId is treated as connection error.
                    if (getStreamId() == 0)
                        return connectionFailure(buffer, ErrorCode.PROTOCOL_ERROR.code, "invalid_continuation_frame");

                    if (getStreamId() != headerBlockFragments.getStreamId())
                        return connectionFailure(buffer, ErrorCode.PROTOCOL_ERROR.code, "invalid_continuation_stream");

                    length = getBodyLength();
                    state = State.FRAGMENT;
                    break;
                }
                case FRAGMENT:
                {
                    int remaining = buffer.remaining();
                    if (remaining < length)
                    {
                        headerBlockFragments.storeFragment(buffer, remaining, false);
                        length -= remaining;
                        break;
                    }
                    else
                    {
                        boolean last = hasFlag(Flags.END_HEADERS);
                        headerBlockFragments.storeFragment(buffer, length, last);
                        reset();
                        if (last)
                            onHeaders();
                        return true;
                    }
                }
                default:
                {
                    throw new IllegalStateException();
                }
            }
        }
        return false;
    }
