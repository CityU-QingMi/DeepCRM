    public static void putDecLong(ByteBuffer buffer, long n)
    {
        if (n < 0)
        {
            buffer.put((byte)'-');

            if (n == Long.MIN_VALUE)
            {
                buffer.put((byte)'9');
                n = 223372036854775808L;
            }
            else
                n = -n;
        }

        if (n < 10)
        {
            buffer.put(DIGIT[(int)n]);
        }
        else
        {
            boolean started = false;
            // This assumes constant time int arithmatic
            for (long aDecDivisorsL : decDivisorsL)
            {
                if (n < aDecDivisorsL)
                {
                    if (started)
                        buffer.put((byte)'0');
                    continue;
                }

                started = true;
                long d = n / aDecDivisorsL;
                buffer.put(DIGIT[(int)d]);
                n = n - d * aDecDivisorsL;
            }
        }
    }
