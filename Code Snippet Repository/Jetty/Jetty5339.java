        @Override
        public void run()
        {
            try
            {
                _task.run();
            }
            catch (Throwable x)
            {
                LOG.warn("Exception while executing task " + _task, x);
            }
        }
