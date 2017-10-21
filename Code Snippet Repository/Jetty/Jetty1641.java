        @Override
        public void close()
        {
            try
            {
                ChannelEndPoint.this.close();
            }
            catch (Throwable x)
            {
                LOG.warn(x);
            }
        }
