    @Override
    public Iterator<ByteBuffer> iterator()
    {
        return new Iterator<ByteBuffer>()
        {
            private int index;

            @Override
            public boolean hasNext()
            {
                return index < buffers.length;
            }

            @Override
            public ByteBuffer next()
            {
                try
                {
                    ByteBuffer buffer = buffers[index];
                    buffers[index] = buffer.slice();
                    ++index;
                    return buffer;
                }
                catch (ArrayIndexOutOfBoundsException x)
                {
                    throw new NoSuchElementException();
                }
            }

            @Override
            public void remove()
            {
                throw new UnsupportedOperationException();
            }
        };
    }
