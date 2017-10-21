    public static void encode(int value,Appendable buf) throws IOException
    {
        buf.append(__rfc1421alphabet[0x3f&((0xFC000000&value)>>26)]);
        buf.append(__rfc1421alphabet[0x3f&((0x03F00000&value)>>20)]);
        buf.append(__rfc1421alphabet[0x3f&((0x000FC000&value)>>14)]);
        buf.append(__rfc1421alphabet[0x3f&((0x00003F00&value)>>8)]);
        buf.append(__rfc1421alphabet[0x3f&((0x000000FC&value)>>2)]);
        buf.append(__rfc1421alphabet[0x3f&((0x00000003&value)<<4)]);
        buf.append('=');
    }
