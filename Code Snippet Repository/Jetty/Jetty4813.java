    public static void putDecInt(ByteBuffer buffer, int n)
    {
        if (n < 0)
        {
            buffer.put((byte)'-');

            if (n == Integer.MIN_VALUE)
            {
                buffer.put((byte)'2');
                n = 147483648;
            }
            else
                n = -n;
        }

        if (n < 10)
        {
            buffer.put(DIGIT[n]);
        }
        else
        {
            boolean started = false;
            // This assumes constant time int arithmatic
            for (int decDivisor : decDivisors)
            {
                if (n < decDivisor)
                {
                    if (started)
                        buffer.put((byte)'0');
                    continue;
                }

                started = true;
                int d = n / decDivisor;
                buffer.put(DIGIT[d]);
                n = n - d * decDivisor;
            }
        }
    }
