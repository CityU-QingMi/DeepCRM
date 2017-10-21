    public static int parseInt(byte[] b, int offset, int length, int base)
        throws NumberFormatException
    {
        int value=0;

        if (length<0)
            length=b.length-offset;

        for (int i=0;i<length;i++)
        {
            char c=(char)(0xff&b[offset+i]);

            int digit=c-'0';
            if (digit<0 || digit>=base || digit>=10)
            {
                digit=10+c-'A';
                if (digit<10 || digit>=base)
                    digit=10+c-'a';
            }
            if (digit<0 || digit>=base)
                throw new NumberFormatException(new String(b,offset,length));
            value=value*base+digit;
        }
        return value;
    }
