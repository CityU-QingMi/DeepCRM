    protected Result generateContent(int id, ByteBuffer content, boolean recycle, boolean lastContent, Callback callback, FCGI.FrameType frameType)
    {
        id &= 0xFF_FF;

        int contentLength = content == null ? 0 : content.remaining();
        Result result = new Result(byteBufferPool, callback);

        while (contentLength > 0 || lastContent)
        {
            ByteBuffer buffer = byteBufferPool.acquire(8, false);
            BufferUtil.clearToFill(buffer);
            result = result.append(buffer, true);

            // Generate the frame header
            buffer.put((byte)0x01);
            buffer.put((byte)frameType.code);
            buffer.putShort((short)id);
            int length = Math.min(MAX_CONTENT_LENGTH, contentLength);
            buffer.putShort((short)length);
            buffer.putShort((short)0);
            BufferUtil.flipToFlush(buffer, 0);

            if (contentLength == 0)
                break;

            // Slice the content to avoid copying
            int limit = content.limit();
            content.limit(content.position() + length);
            ByteBuffer slice = content.slice();
            // Don't recycle the slice
            result = result.append(slice, false);
            content.position(content.limit());
            content.limit(limit);
            contentLength -= length;
            // Recycle the content buffer if needed
            if (recycle && contentLength == 0)
                result = result.append(content, true);
        }

        return result;
    }
