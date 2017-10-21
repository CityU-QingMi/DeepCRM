    private boolean parseRequestBuffer()
    {
        if (LOG.isDebugEnabled())
            LOG.debug("{} parse {} {}",this,BufferUtil.toDetailString(_requestBuffer));

        boolean handle = _parser.parseNext(_requestBuffer==null?BufferUtil.EMPTY_BUFFER:_requestBuffer);

        if (LOG.isDebugEnabled())
            LOG.debug("{} parsed {} {}",this,handle,_parser);

        // recycle buffer ?
        if (_contentBufferReferences.get()==0)
            releaseRequestBuffer();

        return handle;
    }
