		public void execute(JobExecutionContext context) throws JobExecutionException {
			try {
				@SuppressWarnings("unchecked")
				List<Date> jobExecDates = (List<Date>)context.getScheduler().getContext().get(DATE_STAMPS);
                                long firedAt = System.currentTimeMillis();
				jobExecDates.add(new Date(firedAt));
                                long sleepTill = firedAt + JOB_BLOCK_TIME;
                                for (long sleepFor = sleepTill - System.currentTimeMillis(); sleepFor > 0; sleepFor = sleepTill - System.currentTimeMillis()) {
                                  Thread.sleep(sleepFor);
                                }
			} catch (InterruptedException e) {
				throw new JobExecutionException("Failed to pause job for testing.");
			} catch (SchedulerException e) {
				throw new JobExecutionException("Failed to lookup datestamp collection.");
			}
		}
