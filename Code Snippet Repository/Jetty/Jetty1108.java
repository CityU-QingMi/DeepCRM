    public MetaData parse(ByteBuffer buffer, int blockLength)
    {
        // We must wait for the all the bytes of the header block to arrive.
        // If they are not all available, accumulate them.
        // When all are available, decode them.

        int accumulated = blockBuffer == null ? 0 : blockBuffer.position();
        int remaining = blockLength - accumulated;

        if (buffer.remaining() < remaining)
        {
            if (blockBuffer == null)
            {
                blockBuffer = byteBufferPool.acquire(blockLength, false);
                BufferUtil.clearToFill(blockBuffer);
            }
            blockBuffer.put(buffer);
            return null;
        }
        else
        {
            int limit = buffer.limit();
            buffer.limit(buffer.position() + remaining);
            ByteBuffer toDecode;
            if (blockBuffer != null)
            {
                blockBuffer.put(buffer);
                BufferUtil.flipToFlush(blockBuffer, 0);
                toDecode = blockBuffer;
            }
            else
            {
                toDecode = buffer;
            }

            MetaData result = hpackDecoder.decode(toDecode);

            buffer.limit(limit);

            if (blockBuffer != null)
            {
                byteBufferPool.release(blockBuffer);
                blockBuffer = null;
            }

            return result;
        }
    }
