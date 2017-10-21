    public static int takeInt(ByteBuffer buffer)
    {
        int val = 0;
        boolean started = false;
        boolean minus = false;
        int i;
        for (i = buffer.position(); i < buffer.limit(); i++)
        {
            byte b = buffer.get(i);
            if (b <= SPACE)
            {
                if (started)
                    break;
            }
            else if (b >= '0' && b <= '9')
            {
                val = val * 10 + (b - '0');
                started = true;
            }
            else if (b == MINUS && !started)
            {
                minus = true;
            }
            else
                break;
        }

        if (started)
        {
            buffer.position(i);
            return minus ? (-val) : val;
        }
        throw new NumberFormatException(toString(buffer));
    }
