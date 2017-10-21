        public void lifeCycleStopping(LifeCycle event)
        {
            stopping = true;
            stoppingTime = System.currentTimeMillis();

            // need to sleep to make sure the stopping and stopped times are not
            // the same
            try
            {
                Thread.sleep(1);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
