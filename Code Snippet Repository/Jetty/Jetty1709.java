    protected ByteBuffer[] flush(ByteBuffer[] buffers) throws IOException
    {
        boolean progress=true;
        while(progress && buffers!=null)
        {
            int before=buffers.length==0?0:buffers[0].remaining();
            boolean flushed=_endPoint.flush(buffers);
            int r=buffers.length==0?0:buffers[0].remaining();

            if (LOG.isDebugEnabled())
                LOG.debug("Flushed={} {}/{}+{} {}",flushed,before-r,before,buffers.length-1,this);

            if (flushed)
                return null;

            progress=before!=r;

            int not_empty=0;
            while(r==0)
            {
                if (++not_empty==buffers.length)
                {
                    buffers=null;
                    not_empty=0;
                    break;
                }
                progress=true;
                r=buffers[not_empty].remaining();
            }

            if (not_empty>0)
                buffers=Arrays.copyOfRange(buffers,not_empty,buffers.length);
        }

        if (LOG.isDebugEnabled())
            LOG.debug("!fully flushed {}",this);

        // If buffers is null, then flush has returned false but has consumed all the data!
        // This is probably SSL being unable to flush the encrypted buffer, so return EMPTY_BUFFERS
        // and that will keep this WriteFlusher pending.
        return buffers==null?EMPTY_BUFFERS:buffers;
    }
