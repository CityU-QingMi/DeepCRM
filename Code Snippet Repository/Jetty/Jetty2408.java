        private int findLineFeed(ByteBuffer input)
        {
            for (int i = input.position(); i < input.limit(); i++)
            {
                byte b = input.get(i);
                if ((b == (byte)'\n') || (b == (byte)'\r'))
                {
                    return i;
                }
            }
            return -1;
        }
