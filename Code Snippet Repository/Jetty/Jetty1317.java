    private static void putSanitisedValue(String s,ByteBuffer buffer)
    {
        int l=s.length();
        for (int i=0;i<l;i++)
        {
            char c=s.charAt(i);

            if (c<0 || c>0xff || c=='\r' || c=='\n')
                buffer.put((byte)' ');
            else
                buffer.put((byte)(0xff&c));
        }
    }
