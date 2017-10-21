    public static int toInt(ByteBuffer buffer, int position, int length)
    {
        int val = 0;
        boolean started = false;
        boolean minus = false;

        int limit = position+length;
        
        if (length<=0)
            throw new NumberFormatException(toString(buffer,position,length,StandardCharsets.UTF_8));
        
        for (int i = position; i < limit; i++)
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
            return minus ? (-val) : val;
        throw new NumberFormatException(toString(buffer));
    }
