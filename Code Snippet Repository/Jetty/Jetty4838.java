    @Override
    public void write(String s)
        throws IOException
    {
        if (s==null)
        {
            write("null",0,4);
            return;
        }
        
        int length=s.length();
        ensureSpareCapacity(length);
        for (int i=0;i<length;i++)
        {
            char c=s.charAt(i);
            if (c>=0x0&&c<=0x7f)
                _buf[_size++]=(byte)c;
            else
            {
                writeEncoded(s.toCharArray(),i,length-i);
                break;
            }
        }
    }
