    @Override
    public int fill(ByteBuffer buffer) throws IOException
    {
        int filled=0;
        try(Locker.Lock lock = _locker.lock())
        {
            while(true)
            {
                if (!isOpen())
                    throw new EofException("CLOSED");

                if (isInputShutdown())
                    return -1;

                if (_inQ.isEmpty())
                    break;

                ByteBuffer in= _inQ.peek();
                if (in==EOF)
                {
                    filled=-1;
                    break;
                }

                if (BufferUtil.hasContent(in))
                {
                    filled=BufferUtil.append(buffer,in);
                    if (BufferUtil.isEmpty(in))
                        _inQ.poll();
                    break;
                }
                _inQ.poll();
            }
        }

        if (filled>0)
            notIdle();
        else if (filled<0)
            shutdownInput();
        return filled;
    }
