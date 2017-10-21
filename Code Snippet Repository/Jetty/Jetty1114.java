    protected boolean parseHeader(ByteBuffer buffer)
    {
        if (!headerParser.parse(buffer))
            return false;

        FrameType frameType = FrameType.from(getFrameType());
        if (LOG.isDebugEnabled())
            LOG.debug("Parsed {} frame header from {}", frameType, buffer);

        if (continuation)
        {
            if (frameType != FrameType.CONTINUATION)
            {
                // SPEC: CONTINUATION frames must be consecutive.
                BufferUtil.clear(buffer);
                notifyConnectionFailure(ErrorCode.PROTOCOL_ERROR.code, "continuation_frame_expected");
                return false;
            }
            if (headerParser.hasFlag(Flags.END_HEADERS))
            {
                continuation = false;
            }
        }
        else
        {
            if (frameType == FrameType.HEADERS &&
                    !headerParser.hasFlag(Flags.END_HEADERS))
            {
                continuation = true;
            }
        }
        state = State.BODY;
        return true;
    }
