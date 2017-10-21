        @Override
        public void transform(ByteBuffer input, boolean finished, List<ByteBuffer> output) throws IOException
        {
            if (input.hasRemaining())
            {
                ByteBuffer copy = ByteBuffer.allocate(input.remaining());
                copy.put(input).flip();
                buffers.add(copy);
            }

            if (finished)
            {
                Assert.assertFalse(buffers.isEmpty());
                output.addAll(buffers);
                buffers.clear();
            }
        }
