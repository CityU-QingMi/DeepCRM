    public static void putHexInt(ByteBuffer buffer, int n)
    {
        if (n < 0)
        {
            buffer.put((byte)'-');

            if (n == Integer.MIN_VALUE)
            {
                buffer.put((byte)(0x7f & '8'));
                buffer.put((byte)(0x7f & '0'));
                buffer.put((byte)(0x7f & '0'));
                buffer.put((byte)(0x7f & '0'));
                buffer.put((byte)(0x7f & '0'));
                buffer.put((byte)(0x7f & '0'));
                buffer.put((byte)(0x7f & '0'));
                buffer.put((byte)(0x7f & '0'));

                return;
            }
            n = -n;
        }

        if (n < 0x10)
        {
            buffer.put(DIGIT[n]);
        }
        else
        {
            boolean started = false;
            // This assumes constant time int arithmatic
            for (int hexDivisor : hexDivisors)
            {
                if (n < hexDivisor)
                {
                    if (started)
                        buffer.put((byte)'0');
                    continue;
                }

                started = true;
                int d = n / hexDivisor;
                buffer.put(DIGIT[d]);
                n = n - d * hexDivisor;
            }
        }
    }
