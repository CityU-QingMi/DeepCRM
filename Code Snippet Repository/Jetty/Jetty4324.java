    public static byte[] asByteArray(String id, int size)
    {
        if ((id.length() < 0) || (id.length() > (size * 2)))
        {
            throw new IllegalArgumentException(String.format("Invalid ID length of <%d> expected range of <0> to <%d>",id.length(),(size * 2)));
        }

        byte buf[] = new byte[size];
        byte hex;
        int len = id.length();

        int idx = (int)Math.floor(((size * 2) - (double)len) / 2);
        int i = 0;
        if ((len % 2) != 0)
        { // deal with odd numbered chars
            i -= 1;
        }

        for (; i < len; i++)
        {
            hex = 0;
            if (i >= 0)
            {
                hex = (byte)(Character.digit(id.charAt(i),16) << 4);
            }
            i++;
            hex += (byte)(Character.digit(id.charAt(i),16));

            buf[idx] = hex;
            idx++;
        }

        return buf;
    }
