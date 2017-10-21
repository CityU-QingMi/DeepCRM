    private Result committed( ByteBuffer chunk, ByteBuffer content, boolean last)
    {
        int len = BufferUtil.length(content);

        // handle the content.
        if (len>0)
        {
            if (isChunking())
            {
                if (chunk==null)
                    return Result.NEED_CHUNK;
                BufferUtil.clearToFill(chunk);
                prepareChunk(chunk,len);
                BufferUtil.flipToFlush(chunk,0);
            }
            _contentPrepared+=len;
        }

        if (last)
        {
            _state=State.COMPLETING;
            return len>0?Result.FLUSH:Result.CONTINUE;
        }
        return len>0?Result.FLUSH:Result.DONE;
    }
