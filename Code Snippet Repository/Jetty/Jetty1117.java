    public boolean parse(ByteBuffer buffer)
    {
        while (buffer.hasRemaining())
        {
            int currByte = buffer.get();
            if (currByte != PrefaceFrame.PREFACE_BYTES[cursor])
            {
                BufferUtil.clear(buffer);
                notifyConnectionFailure(ErrorCode.PROTOCOL_ERROR.code, "invalid_preface");
                return false;
            }
            ++cursor;
            if (cursor == PrefaceFrame.PREFACE_BYTES.length)
            {
                cursor = 0;
                if (LOG.isDebugEnabled())
                    LOG.debug("Parsed preface bytes from {}", buffer);
                return true;
            }
        }
        return false;
    }
