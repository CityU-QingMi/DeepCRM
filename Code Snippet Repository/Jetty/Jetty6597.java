    private static boolean supplyInput(Deflater deflater, ByteBuffer buf)
    {
        if (buf == null || buf.remaining() <= 0)
        {
            if (LOG.isDebugEnabled())
            {
                LOG.debug("No data left left to supply to Deflater");
            }
            return false;
        }

        byte input[];
        int inputOffset;
        int len;

        if (buf.hasArray())
        {
            // no need to create a new byte buffer, just return this one.
            len = buf.remaining();
            input = buf.array();
            inputOffset = buf.position() + buf.arrayOffset();
            buf.position(buf.position() + len);
        }
        else
        {
            // Only create an return byte buffer that is reasonable in size
            len = Math.min(INPUT_MAX_BUFFER_SIZE,buf.remaining());
            input = new byte[len];
            inputOffset = 0;
            buf.get(input,0,len);
        }

        deflater.setInput(input,inputOffset,len);
        if (LOG.isDebugEnabled())
        {
            LOG.debug("Supplied {} input bytes: {}",input.length,toDetail(deflater));
        }
        return true;
    }
