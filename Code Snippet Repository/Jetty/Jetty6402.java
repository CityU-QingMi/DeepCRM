    public static String join(Object[] objs, String delim)
    {
        if (objs == null)
        {
            return "";
        }
        StringBuilder ret = new StringBuilder();
        int len = objs.length;
        for (int i = 0; i < len; i++)
        {
            if (i > 0)
            {
                ret.append(delim);
            }
            if (objs[i] instanceof String)
            {
                ret.append('"').append(objs[i]).append('"');
            }
            else
            {
                ret.append(objs[i]);
            }
        }
        return ret.toString();
    }
