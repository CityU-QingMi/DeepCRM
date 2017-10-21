        @Override
        public void run()
        {
            try
            {
                sconnection = server.accept();
                sconnection.setSoTimeout(60000);
                sconnection.upgrade();
                sconnection.startEcho();
            }
            catch (Exception e)
            {
                LOG.warn(e);
            }
            finally
            {
                connectLatch.countDown();
            }
        }
