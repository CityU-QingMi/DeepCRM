        @Override
        public int read(byte[] b, int offset, int length) throws IOException
        {
            try
            {
                int result;
                Callback callback = null;
                synchronized (lock)
                {
                    DeferredContentProvider.Chunk chunk;
                    while (true)
                    {
                        chunk = chunks.peek();
                        if (chunk == EOF)
                            return -1;

                        if (chunk != null)
                            break;

                        if (failure != null)
                            throw toIOException(failure);

                        if (closed)
                            throw new AsynchronousCloseException();

                        lock.wait();
                    }

                    ByteBuffer buffer = chunk.buffer;
                    result = Math.min(buffer.remaining(), length);
                    buffer.get(b, offset, result);
                    if (!buffer.hasRemaining())
                    {
                        callback = chunk.callback;
                        chunks.poll();
                    }
                }
                if (callback != null)
                    callback.succeeded();
                return result;
            }
            catch (InterruptedException x)
            {
                throw new InterruptedIOException();
            }
        }
