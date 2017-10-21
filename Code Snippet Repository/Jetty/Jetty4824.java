    private static void appendContentChar(StringBuilder buf, byte b)
    {
        if (b == '\\')
            buf.append("\\\\");   
        else if (b >= ' ')
            buf.append((char)b);
        else if (b == '\r')
            buf.append("\\r");
        else if (b == '\n')
            buf.append("\\n");
        else if (b == '\t')
            buf.append("\\t");
        else
            buf.append("\\x").append(TypeUtil.toHexString(b));
    }
