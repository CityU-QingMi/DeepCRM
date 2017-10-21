    protected ByteBuffer getIndirectBuffer(Resource resource)
    {
        try
        {
            return BufferUtil.toBuffer(resource,true);
        }
        catch(IOException|IllegalArgumentException e)
        {
            LOG.warn(e);
            return null;
        }
    }
