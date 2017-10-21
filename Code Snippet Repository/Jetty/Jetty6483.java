        public void send(byte op, byte[] data, int maxFragmentLength)
        {
            _starts.add(System.nanoTime());

            int off = 0;
            int len = data.length;
            if ((maxFragmentLength > 0) && (len > maxFragmentLength))
            {
                len = maxFragmentLength;
            }
            __messagesSent++;
            while (off < data.length)
            {
                __framesSent++;

                off += len;
                if ((data.length - off) > len)
                {
                    len = data.length - off;
                }
                if ((maxFragmentLength > 0) && (len > maxFragmentLength))
                {
                    len = maxFragmentLength;
                }
            }
        }
