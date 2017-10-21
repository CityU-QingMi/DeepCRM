    public static String printable(byte[] b)
    {
        StringBuilder buf = new StringBuilder();
        for (int i=0;i<b.length;i++)
        {
            char c=(char)b[i];
            if (Character.isWhitespace(c)|| c>' ' && c<0x7f)
                buf.append(c);
            else 
            {
                buf.append("0x");
                TypeUtil.toHex(b[i],buf);
            }
        }
        return buf.toString();
    }
