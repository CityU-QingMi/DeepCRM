    public static String deobfuscate(String s)
    {
        if (s.startsWith(__OBFUSCATE)) s = s.substring(4);

        byte[] b = new byte[s.length() / 2];
        int l = 0;
        for (int i = 0; i < s.length(); i += 4)
        {
            if (s.charAt(i)=='U')
            {
                i++;
                String x = s.substring(i, i + 4);
                int i0 = Integer.parseInt(x, 36);
                byte bx = (byte)(i0>>8);
                b[l++] = bx;
            }
            else
            {
                String x = s.substring(i, i + 4);
                int i0 = Integer.parseInt(x, 36);
                int i1 = (i0 / 256);
                int i2 = (i0 % 256);
                byte bx = (byte) ((i1 + i2 - 254) / 2);
                b[l++] = bx;
            }
        }

        return new String(b, 0, l,StandardCharsets.UTF_8);
    }
