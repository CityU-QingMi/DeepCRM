        @Override
        public void run()
        {
            try
            {
                if (_key==null)
                {
                    _key = _channel.register(_selector, SelectionKey.OP_ACCEPT, this);
                }     

                if (LOG.isDebugEnabled())
                    LOG.debug("{} acceptor={}", this, _key);
            }
            catch (Throwable x)
            {
                closeNoExceptions(_channel);
                LOG.warn(x);
            }
        }
