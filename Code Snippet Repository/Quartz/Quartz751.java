        @Override
        public void execute(JobExecutionContext context) throws JobExecutionException {
            long now = System.currentTimeMillis();
            int tic = 0;
            _log.info("Started - " + now);
            try {
                while (runForever) {
                    Thread.sleep(1000);
                    _log.info("Tic " + (++tic) + "- " + now);
                }
                _log.info("Stopped - " + now);
            } catch (InterruptedException e) {
                _log.info("Interrupted - " + now);
            }
        }
