        public void lifeCycleStarting(LifeCycle event)
        {
            starting = true;
            startingTime = System.currentTimeMillis();

            // need to sleep to make sure the starting and started times are not
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
