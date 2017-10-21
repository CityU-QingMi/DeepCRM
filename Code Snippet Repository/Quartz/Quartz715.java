		@Override
		public void jobWasExecuted(JobExecutionContext context,
				JobExecutionException jobException) {
			if(jobExCount.incrementAndGet() == jobExecutionCountToSyncAfter) {
				try {
					CyclicBarrier barrier =  (CyclicBarrier)context.getScheduler().getContext().get(BARRIER);
					barrier.await(125, TimeUnit.SECONDS);
				} catch (Throwable e) {
					e.printStackTrace();
					throw new AssertionError("Await on barrier was interrupted: " + e.toString());
				} 
			}
		}
