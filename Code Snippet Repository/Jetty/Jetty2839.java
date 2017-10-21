    @Override
    public ByteBuffer onUpgradeFrom()
    {
        if (BufferUtil.hasContent(_requestBuffer))
        {
            ByteBuffer buffer = _requestBuffer;
            _requestBuffer=null;
            return buffer;
        }
        return null;
    }
