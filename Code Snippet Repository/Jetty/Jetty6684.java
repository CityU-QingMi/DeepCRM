    public String toPartialString(ByteBuffer buf)
    {
        if (buf == null)
        {
            // no change, return empty
            return "";
        }
        utf8.append(buf);
        String ret = str.toString();
        str.setLength(0);
        return ret;
    }
