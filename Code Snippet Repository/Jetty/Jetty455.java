        @Override
        public void close()
        {
            try
            {
                if (bufferPool != null && buffer != null)
                    bufferPool.release(buffer);
                if (channel != null)
                    channel.close();
            }
            catch (Throwable x)
            {
                LOG.ignore(x);
            }
        }
