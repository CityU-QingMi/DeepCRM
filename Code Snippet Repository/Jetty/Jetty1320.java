    private Result completing( ByteBuffer chunk, ByteBuffer content)
    {
        if (BufferUtil.hasContent(content))
        {
            if (LOG.isDebugEnabled())
                LOG.debug("discarding content in COMPLETING");
            BufferUtil.clear(content);
        }

        if (isChunking())
        {
            if (_trailers!=null)
            {
                // Do we need a chunk buffer?
                if (chunk==null || chunk.capacity()<=CHUNK_SIZE)
                    return Result.NEED_CHUNK_TRAILER;
                
                HttpFields trailers = _trailers.get();

                if (trailers!=null)
                {
                    // Write the last chunk
                    BufferUtil.clearToFill(chunk);
                    generateTrailers(chunk,trailers);
                    BufferUtil.flipToFlush(chunk,0);
                    _endOfContent=EndOfContent.UNKNOWN_CONTENT;
                    return Result.FLUSH;
                }
            }

            // Do we need a chunk buffer?
            if (chunk==null)
                return Result.NEED_CHUNK;

            // Write the last chunk
            BufferUtil.clearToFill(chunk);
            prepareChunk(chunk,0);
            BufferUtil.flipToFlush(chunk,0);
            _endOfContent=EndOfContent.UNKNOWN_CONTENT;
            return Result.FLUSH;   
        }

        _state=State.END;
       return Boolean.TRUE.equals(_persistent)?Result.DONE:Result.SHUTDOWN_OUT;

    }
