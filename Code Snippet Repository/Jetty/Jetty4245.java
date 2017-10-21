    private byte[] addressToBytes(String address)
    {
        Matcher ipv4Matcher = IPv4_PATTERN.matcher(address);
        if (ipv4Matcher.matches())
        {
            byte[] result = new byte[4];
            for (int i = 0; i < result.length; ++i)
                result[i] = Integer.valueOf(ipv4Matcher.group(i + 1)).byteValue();
            return result;
        }
        else
        {
            Matcher ipv6Matcher = IPv6_PATTERN.matcher(address);
            if (ipv6Matcher.matches())
            {
                byte[] result = new byte[16];
                for (int i = 0; i < result.length; i += 2)
                {
                    int word = Integer.valueOf(ipv6Matcher.group(i / 2 + 1), 16);
                    result[i] = (byte)((word & 0xFF00) >>> 8);
                    result[i + 1] = (byte)(word & 0xFF);
                }
                return result;
            }
        }
        return null;
    }
