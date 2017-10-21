        @Override
        public void transform(ByteBuffer input, boolean finished, List<ByteBuffer> output) throws IOException
        {
            if (!input.hasRemaining())
            {
                if (finished)
                    transformer.transform(input, true, buffers);
            }
            else
            {
                while (input.hasRemaining())
                {
                    ByteBuffer decoded = decoder.decode(input);
                    if (decoded.hasRemaining())
                        transformer.transform(decoded, finished && !input.hasRemaining(), buffers);
                }
            }

            if (!buffers.isEmpty() || finished)
            {
                ByteBuffer result = gzip(buffers, finished);
                buffers.clear();
                output.add(result);
            }
        }
