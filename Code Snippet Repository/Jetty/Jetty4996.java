    @Override
    public synchronized int read(byte[] buf, int off, int len) throws IOException
    {
        if (_skipLF && len>0)
        {
            _skipLF=false;
            if (_seenCRLF)
            {
                int b = super.read();
                if (b==-1)
                    return -1;
                
                if (b!='\n')
                {
                    buf[off]=(byte)(0xff&b);
                    return 1+super.read(buf,off+1,len-1);
                }
            }
        }
        
        return super.read(buf,off,len);
    }
