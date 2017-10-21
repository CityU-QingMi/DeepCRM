    @Override
    public void write(char[] ca,int offset, int length)
        throws IOException
    {
        ensureSpareCapacity(length);
        for (int i=0;i<length;i++)
        {
            char c=ca[offset+i];
            if (c>=0&&c<=0x7f)
                _buf[_size++]=(byte)c;
            else
            {
                writeEncoded(ca,offset+i,length-i);
                break;
            }
        }
    }
