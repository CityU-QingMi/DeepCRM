    public static String toDetailString(ByteBuffer[] buffer)
    {
        StringBuilder builder = new StringBuilder();
        builder.append('[');
        for (int i = 0; i < buffer.length; i++)
        {
            if (i > 0) builder.append(',');
            builder.append(toDetailString(buffer[i]));
        }
        builder.append(']');
        return builder.toString();
    }
