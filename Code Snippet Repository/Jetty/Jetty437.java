        @Override
        public boolean hasNext()
        {
            try
            {
                if (hasNext != null)
                    return hasNext;

                byte[] bytes = new byte[bufferSize];
                int read = stream.read(bytes);
                if (LOG.isDebugEnabled())
                    LOG.debug("Read {} bytes from {}", read, stream);
                if (read > 0)
                {
                    hasNext = Boolean.TRUE;
                    buffer = onRead(bytes, 0, read);
                    return true;
                }
                else if (read < 0)
                {
                    hasNext = Boolean.FALSE;
                    buffer = null;
                    close();
                    return false;
                }
                else
                {
                    hasNext = Boolean.TRUE;
                    buffer = BufferUtil.EMPTY_BUFFER;
                    return true;
                }
            }
            catch (Throwable x)
            {
                if (LOG.isDebugEnabled())
                    LOG.debug(x);
                if (failure == null)
                {
                    failure = x;
                    onReadFailure(x);
                    // Signal we have more content to cause a call to
                    // next() which will throw NoSuchElementException.
                    hasNext = Boolean.TRUE;
                    buffer = null;
                    close();
                    return true;
                }
                throw new IllegalStateException();
            }
        }
