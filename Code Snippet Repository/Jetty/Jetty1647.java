    @Override
    public boolean flush(ByteBuffer... buffers) throws IOException
    {
        long flushed=0;
        try
        {
            if (buffers.length==1)
                flushed=_channel.write(buffers[0]);
            else if (_gather!=null && buffers.length>1)
                flushed=_gather.write(buffers,0,buffers.length);
            else
            {
                for (ByteBuffer b : buffers)
                {
                    if (b.hasRemaining())
                    {
                        int l=_channel.write(b);
                        if (l>0)
                            flushed+=l;
                        if (b.hasRemaining())
                            break;
                    }
                }
            }
            if (LOG.isDebugEnabled())
                LOG.debug("flushed {} {}", flushed, this);
        }
        catch (IOException e)
        {
            throw new EofException(e);
        }

        if (flushed>0)
            notIdle();

        for (ByteBuffer b : buffers)
            if (!BufferUtil.isEmpty(b))
                return false;

        return true;
    }
