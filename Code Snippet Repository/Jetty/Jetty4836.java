    @Override
    public void write(char[] ca)
        throws IOException
    {
        ensureSpareCapacity(ca.length);
        for (int i=0;i<ca.length;i++)
        {
            char c=ca[i];
            if (c>=0&&c<=0x7f)
                _buf[_size++]=(byte)c;
            else
            {
                writeEncoded(ca,i,ca.length-i);
                break;
            }
        }
    }
