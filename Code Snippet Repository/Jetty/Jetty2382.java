        @Override
        protected Action process() throws Exception
        {
            buffer = bufferPool.acquire(getInputBufferSize(), true);
            try
            {
                int filled = this.filled = read(getEndPoint(), buffer);
                if (LOG.isDebugEnabled())
                    LOG.debug("{} filled {} bytes", ProxyConnection.this, filled);
                if (filled > 0)
                {
                    write(connection.getEndPoint(), buffer, this);
                    return Action.SCHEDULED;
                }
                else if (filled == 0)
                {
                    bufferPool.release(buffer);
                    fillInterested();
                    return Action.IDLE;
                }
                else
                {
                    bufferPool.release(buffer);
                    connection.getEndPoint().shutdownOutput();
                    return Action.SUCCEEDED;
                }
            }
            catch (IOException x)
            {
                if (LOG.isDebugEnabled())
                    LOG.debug(ProxyConnection.this + " could not fill", x);
                bufferPool.release(buffer);
                disconnect();
                return Action.SUCCEEDED;
            }
        }
