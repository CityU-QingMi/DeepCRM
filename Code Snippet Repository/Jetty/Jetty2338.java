            @Override
            public void write(byte[] b, int off, int len) throws IOException
            {
                if (len <= 0)
                    return;

                outputBufferSize += len;
                long max = getMaxOutputBufferSize();
                if (max >= 0 && outputBufferSize > max)
                {
                    overflow(ByteBuffer.wrap(b, off, len));
                }
                else
                {
                    // The array may be reused by the
                    // application so we need to copy it.
                    byte[] copy = new byte[len];
                    System.arraycopy(b, off, copy, 0, len);
                    sinkBuffers.add(ByteBuffer.wrap(copy));
                }
            }
