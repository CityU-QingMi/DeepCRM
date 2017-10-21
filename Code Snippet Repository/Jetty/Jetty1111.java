    @Override
    protected void emptyBody(ByteBuffer buffer)
    {
        if (hasFlag(Flags.END_HEADERS))
        {
            MetaData metaData = headerBlockParser.parse(BufferUtil.EMPTY_BUFFER, 0);
            onHeaders(0, 0, false, metaData);
        }
        else
        {
            headerBlockFragments.setStreamId(getStreamId());
            headerBlockFragments.setEndStream(isEndStream());
            if (hasFlag(Flags.PRIORITY))
                connectionFailure(buffer, ErrorCode.PROTOCOL_ERROR.code, "invalid_headers_priority_frame");
        }
    }
