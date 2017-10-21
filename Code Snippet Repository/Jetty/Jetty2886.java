        public void waitUntilClosed()
        {
            while (isOpen())
            {
                try
                {
                    if (!_closed.await(10,TimeUnit.SECONDS))
                        break;
                }
                catch(Exception e)
                {
                    LOG.warn(e);
                }
            }
        }
