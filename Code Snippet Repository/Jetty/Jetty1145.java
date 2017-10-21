    public static String toASCIIString(ByteBuffer buffer,int length)
    {
        StringBuilder builder = new StringBuilder(length);
        int position=buffer.position();
        int start=buffer.arrayOffset()+ position;
        int end=start+length;
        buffer.position(position+length);
        byte[] array=buffer.array();
        for (int i=start;i<end;i++)
            builder.append((char)(0x7f&array[i]));
        return builder.toString();
    }
