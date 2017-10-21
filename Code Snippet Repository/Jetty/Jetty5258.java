    public static String obfuscate(String s)
    {
        StringBuilder buf = new StringBuilder();
        byte[] b = s.getBytes(StandardCharsets.UTF_8);

        buf.append(__OBFUSCATE);
        for (int i = 0; i < b.length; i++)
        {
            byte b1 = b[i];
            byte b2 = b[b.length - (i + 1)];
            if (b1<0 || b2<0)
            {
                int i0 = (0xff&b1)*256 + (0xff&b2);
                String x = Integer.toString(i0, 36).toLowerCase(Locale.ENGLISH);
                buf.append("U0000",0,5-x.length());
                buf.append(x);
            }
            else
            {
                int i1 = 127 + b1 + b2;
                int i2 = 127 + b1 - b2;
                int i0 = i1 * 256 + i2;
                String x = Integer.toString(i0, 36).toLowerCase(Locale.ENGLISH);

                int j0 = Integer.parseInt(x, 36);
                int j1 = (i0 / 256);
                int j2 = (i0 % 256);
                byte bx = (byte) ((j1 + j2 - 254) / 2);

                buf.append("000",0,4-x.length());
                buf.append(x);
            }

        }
        return buf.toString();
    }
