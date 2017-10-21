        public void awaitRun()
        {
            try
            {
                _run.await();
            }
            catch (InterruptedException e)
            {
                throw new IllegalStateException(e);
            }
        }
