    public static int decode(ByteBuffer buffer, int n)
    {
        if (n==8)
        {
            int nbits = 0xFF;

            int i=buffer.get()&0xff;
            
            if (i == nbits)
            {       
                int m=1;
                int b;
                do
                {
                    b = 0xff&buffer.get();
                    i = i + (b&127) * m;
                    m = m*128;
                }
                while ((b&128) == 128);
            }
            return i;
        }
        
        int nbits = 0xFF >>> (8 - n);

        int i=buffer.get(buffer.position()-1)&nbits;
        
        if (i == nbits)
        {       
            int m=1;
            int b;
            do
            {
                b = 0xff&buffer.get();
                i = i + (b&127) * m;
                m = m*128;
            }
            while ((b&128) == 128);
        }
        return i;
    }
