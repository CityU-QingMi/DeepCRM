    protected boolean decodedChunk(ByteBuffer chunk)
    {
        if (_inflated==null)
        {
            _inflated=chunk;
        }
        else
        {
            int size = _inflated.remaining() + chunk.remaining();
            if (size<=_inflated.capacity())
            {
                BufferUtil.append(_inflated,chunk);
                BufferUtil.put(chunk,_inflated);
                release(chunk);
            }
            else
            {
                ByteBuffer bigger=acquire(size);
                int pos=BufferUtil.flipToFill(bigger);
                BufferUtil.put(_inflated,bigger);
                BufferUtil.put(chunk,bigger);
                BufferUtil.flipToFlush(bigger,pos);
                release(_inflated);
                release(chunk);
                _inflated = bigger;
            }
        }
        return false;
    }
