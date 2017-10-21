    public static String toSystemLN(String str)
    {
        boolean linesep = false;
        StringBuffer ret = new StringBuffer();
        for (char c : str.toCharArray())
        {
            switch (c)
            {
                case '\r':
                    linesep = true;
                    break;
                case '\n':
                    linesep = true;
                    break;
                default:
                    if (linesep)
                    {
                        ret.append(LN);
                        linesep = false;
                    }
                ret.append(c);
            }
        }

        return ret.toString();
    }
