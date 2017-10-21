    public static String asHex(byte buf[])
    {
        int len = buf.length;
        char out[] = new char[len * 2];
        for (int i = 0; i < len; i++)
        {
            out[i * 2] = hexcodes[(buf[i] & 0xF0) >> 4];
            out[(i * 2) + 1] = hexcodes[(buf[i] & 0x0F)];
        }
        return String.valueOf(out);
    }
