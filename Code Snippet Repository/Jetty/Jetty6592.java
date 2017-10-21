    protected void decompress(ByteAccumulator accumulator, ByteBuffer buf) throws DataFormatException
    {
        if ((buf == null) || (!buf.hasRemaining()))
        {
            return;
        }
        byte[] output = new byte[DECOMPRESS_BUF_SIZE];
        
        Inflater inflater = getInflater();
        
        while(buf.hasRemaining() && inflater.needsInput())
        {
            if (!supplyInput(inflater,buf))
            {
                LOG.debug("Needed input, but no buffer could supply input");
                return;
            }
    
            int read;
            while ((read = inflater.inflate(output)) >= 0)
            {
                if (read == 0)
                {
                    LOG.debug("Decompress: read 0 {}",toDetail(inflater));
                    break;
                }
                else
                {
                    // do something with output
                    if (LOG.isDebugEnabled())
                    {
                        LOG.debug("Decompressed {} bytes: {}",read,toDetail(inflater));
                    }
    
                    accumulator.copyChunk(output,0,read);
                }
            }
        }

        if (LOG.isDebugEnabled())
        {
            LOG.debug("Decompress: exiting {}",toDetail(inflater));
        }
    }
