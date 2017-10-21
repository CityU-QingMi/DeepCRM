    @Override
    public ByteBuffer getDirectBuffer()
    {
        if (_resource.length()<=0 || _maxBuffer>0 && _maxBuffer<_resource.length())
            return null;
        try
        {
            return BufferUtil.toBuffer(_resource,true);
        }
        catch(IOException e)
        {
            throw new RuntimeException(e);
        }
    }
