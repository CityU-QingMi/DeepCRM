    public void write(char c)
        throws IOException
    {
        ensureSpareCapacity(1);
        if (c>=0&&c<=0x7f)
            _buf[_size++]=(byte)c;
        else
        {
            char[] ca ={c};
            writeEncoded(ca,0,1);
        }
    }
