    protected ByteBuffer getMappedBuffer(Resource resource)
    {
        // Only use file mapped buffers for cached resources, otherwise too much virtual memory commitment for
        // a non shared resource.  Also ignore max buffer size
        try
        {
            if (_useFileMappedBuffer && resource.getFile()!=null && resource.length()<Integer.MAX_VALUE) 
                return BufferUtil.toMappedBuffer(resource.getFile());            
        }
        catch(IOException|IllegalArgumentException e)
        {
            LOG.warn(e);
        }
        return null;
    }
