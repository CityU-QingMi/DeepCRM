        @Override
        public int read(byte[] b, int off, int len) throws IOException
        {
            if (len == 0)
                return 0;
            if (index == sourceBuffers.size())
                return -1;

            if (slice == null)
                slice = sourceBuffers.get(index).slice();

            int size = Math.min(len, slice.remaining());
            slice.get(b, off, size);

            if (!slice.hasRemaining())
            {
                ++index;
                slice = null;
            }

            return size;
        }
