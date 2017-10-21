    public static byte[] fromHexString(String s)
    {
        if (s.length()%2!=0)
            throw new IllegalArgumentException(s);
        byte[] array = new byte[s.length()/2];
        for (int i=0;i<array.length;i++)
        {
            int b = Integer.parseInt(s.substring(i*2,i*2+2),16);
            array[i]=(byte)(0xff&b);
        }
        return array;
    }
