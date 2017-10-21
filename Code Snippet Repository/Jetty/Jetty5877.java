        @Override
        public void run()
        {
            try
            {
                _thread=Thread.currentThread();
                _run.countDown();
                _block.await();
            }
            catch (InterruptedException e)
            {
                throw new IllegalStateException(e);
            }
            finally
            {
                _thread=null;
            }
        }
