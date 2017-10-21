    public static String maxStringLength(int max, String raw)
    {
        int length = raw.length();
        if (length <= max)
        {
            // already short enough
            return raw;
        }

        if (max < 9)
        {
            // minimum supported
            return raw.substring(0,max);
        }

        StringBuilder ret = new StringBuilder();
        int startLen = (int)Math.round((double)max / (double)3);
        ret.append(raw.substring(0,startLen));
        ret.append("...");
        ret.append(raw.substring(length - (max - startLen - 3)));

        return ret.toString();
    }
