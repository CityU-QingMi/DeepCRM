        public void run()
        {
            try
            {
                _run.countDown();
                _stopping.await();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
                _jobs.incrementAndGet();
                _stopped.countDown();
            }
        }
