        @Override 
        public void lifeCycleFailure(LifeCycle lifecycle, Throwable cause) 
        {
            try
            {
                lifecycle.stop();
            }
            catch(Exception e)
            {
                cause.addSuppressed(e);
            }
        }
