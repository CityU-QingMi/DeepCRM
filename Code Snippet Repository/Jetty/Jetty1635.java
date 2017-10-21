    @Override
    public boolean flush(ByteBuffer... buffers) throws IOException
    {
        boolean flushed=true;
        try(Locker.Lock lock = _locker.lock())
        {
            if (!isOpen())
                throw new IOException("CLOSED");
            if (isOutputShutdown())
                throw new IOException("OSHUT");
            
            boolean idle=true;

            for (ByteBuffer b : buffers)
            {
                if (BufferUtil.hasContent(b))
                {
                    if (_growOutput && b.remaining()>BufferUtil.space(_out))
                    {
                        BufferUtil.compact(_out);
                        if (b.remaining()>BufferUtil.space(_out))
                        {
                            ByteBuffer n = BufferUtil.allocate(_out.capacity()+b.remaining()*2);
                            BufferUtil.append(n,_out);
                            _out=n;
                        }
                    }

                    if (BufferUtil.append(_out,b)>0)
                        idle=false;

                    if (BufferUtil.hasContent(b))
                    {
                        flushed=false;
                        break;
                    }
                }
            }
            if (!idle)
            {
                notIdle();
                _hasOutput.signalAll();
            }
        }
        return flushed;
    }
