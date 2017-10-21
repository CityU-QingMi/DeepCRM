    public static int octectsNeeded(int n,int i)
    {
        if (n==8)
        {
            int nbits = 0xFF;
            i=i-nbits;
            if (i<0)
                return 1;
            if (i==0)
                return 2;
            int lz=Integer.numberOfLeadingZeros(i);
            int log=32-lz;
            return 1+(log+6)/7;
        }
        
        int nbits = 0xFF >>> (8 - n);
        i=i-nbits;
        if (i<0)
            return 0;
        if (i==0)
            return 1;
        int lz=Integer.numberOfLeadingZeros(i);
        int log=32-lz;
        return (log+6)/7;
    }
