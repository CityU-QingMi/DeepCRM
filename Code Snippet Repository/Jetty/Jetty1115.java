    protected boolean parseBody(ByteBuffer buffer)
    {
        int type = getFrameType();
        if (type < 0 || type >= bodyParsers.length)
        {
            BufferUtil.clear(buffer);
            notifyConnectionFailure(ErrorCode.PROTOCOL_ERROR.code, "unknown_frame_type_" + type);
            return false;
        }

        BodyParser bodyParser = bodyParsers[type];
        if (headerParser.getLength() == 0)
        {
            bodyParser.emptyBody(buffer);
        }
        else
        {
            if (!bodyParser.parse(buffer))
                return false;
        }
        if (LOG.isDebugEnabled())
            LOG.debug("Parsed {} frame body from {}", FrameType.from(type), buffer);
        reset();
        return true;
    }
