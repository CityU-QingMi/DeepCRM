        @Override
        public ByteBuffer next()
        {
            try
            {
                if (channel == null)
                {
                    buffer = bufferPool == null ?
                            ByteBuffer.allocateDirect(bufferSize) :
                            bufferPool.acquire(bufferSize, true);
                    channel = Files.newByteChannel(filePath, StandardOpenOption.READ);
                    if (LOG.isDebugEnabled())
                        LOG.debug("Opened file {}", filePath);
                }

                buffer.clear();
                int read = channel.read(buffer);
                if (read < 0)
                    throw new NoSuchElementException();

                if (LOG.isDebugEnabled())
                    LOG.debug("Read {} bytes from {}", read, filePath);

                position += read;

                buffer.flip();
                return buffer;
            }
            catch (NoSuchElementException x)
            {
                close();
                throw x;
            }
            catch (Throwable x)
            {
                close();
                throw (NoSuchElementException)new NoSuchElementException().initCause(x);
            }
        }
