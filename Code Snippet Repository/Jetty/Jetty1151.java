    private static void encode(final int[][] table,ByteBuffer buffer,String s)
    {
        long current = 0;
        int n = 0;

        byte[] array = buffer.array();
        int p=buffer.arrayOffset()+buffer.position();

        int len = s.length();
        for (int i=0;i<len;i++)
        {
            char c=s.charAt(i);
            if (c>=128 || c<' ')
                throw new IllegalArgumentException();
            int code = table[c][0];
            int bits = table[c][1];

            current <<= bits;
            current |= code;
            n += bits;

            while (n >= 8) 
            {
                n -= 8;
                array[p++]=(byte)(current >> n);
            }
        }

        if (n > 0) 
        {
          current <<= (8 - n);
          current |= (0xFF >>> n); 
          array[p++]=(byte)current;
        }
        
        buffer.position(p-buffer.arrayOffset());
    }
