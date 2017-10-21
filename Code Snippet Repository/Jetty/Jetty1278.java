    public ByteBuffer decode(ByteBuffer compressed)
    {
        decodeChunks(compressed);
        if (BufferUtil.isEmpty(_inflated) || _state==State.CRC || _state==State.ISIZE )
            return BufferUtil.EMPTY_BUFFER;
        
        ByteBuffer result = _inflated;
        _inflated = null;
        return result;
    }
