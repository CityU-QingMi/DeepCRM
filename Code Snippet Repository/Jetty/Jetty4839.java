    @Override
    public void write(String s,int offset, int length)
        throws IOException
    {
        ensureSpareCapacity(length);
        for (int i=0;i<length;i++)
        {
            char c=s.charAt(offset+i);
            if (c>=0&&c<=0x7f)
                _buf[_size++]=(byte)c;
            else
            {
                writeEncoded(s.toCharArray(),offset+i,length-i);
                break;
            }
        }
    }
