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
                    if (getStreamId() != 0)
                        return connectionFailure(buffer, ErrorCode.PROTOCOL_ERROR.code, "invalid_ping_frame");
                    // SPEC: wrong body length is treated as connection error.
                    if (getBodyLength() != 8)
                        return connectionFailure(buffer, ErrorCode.FRAME_SIZE_ERROR.code, "invalid_ping_frame");
                    state = State.PAYLOAD;
                    break;
                }
                case PAYLOAD:
                {
                    payload = new byte[8];
                    if (buffer.remaining() >= 8)
                    {
                        buffer.get(payload);
                        return onPing(payload);
                    }
                    else
                    {
                        state = State.PAYLOAD_BYTES;
                        cursor = 8;
                    }
                    break;
                }
                case PAYLOAD_BYTES:
                {
                    payload[8 - cursor] = buffer.get();
                    --cursor;
                    if (cursor == 0)
                        return onPing(payload);
                    break;
                }
                default:
                {
                    throw new IllegalStateException();
                }
            }
        }
        return false;
    }
