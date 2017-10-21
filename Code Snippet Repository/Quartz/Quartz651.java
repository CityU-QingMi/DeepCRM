        public void execute(JobExecutionContext context)
                throws JobExecutionException {
        	
			try {
				@SuppressWarnings("unchecked")
				List<Long> jobExecTimestamps = (List<Long>)context.getScheduler().getContext().get(DATE_STAMPS);
				CyclicBarrier barrier =  (CyclicBarrier)context.getScheduler().getContext().get(BARRIER);

	        	jobExecTimestamps.add(System.currentTimeMillis());
	        	
				barrier.await(TEST_TIMEOUT_SECONDS, TimeUnit.SECONDS);
			} catch (Throwable e) {
				e.printStackTrace();
				throw new AssertionError("Await on barrier was interrupted: " + e.toString());
			} 
        }
