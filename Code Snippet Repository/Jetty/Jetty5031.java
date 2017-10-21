    public static int toInt(String string,int from)
    {
        int val = 0;
        boolean started = false;
        boolean minus = false;

        for (int i = from; i < string.length(); i++)
        {
            char b = string.charAt(i);
            if (b <= ' ')
            {
                if (started)
                    break;
            }
            else if (b >= '0' && b <= '9')
            {
                val = val * 10 + (b - '0');
                started = true;
            }
            else if (b == '-' && !started)
            {
                minus = true;
            }
            else
                break;
        }

        if (started)
            return minus?(-val):val;
        throw new NumberFormatException(string);
    }
