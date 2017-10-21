	@Override
	public void execute(Runnable task, long startTimeout) {
		Work work = new DelegatingWork(this.taskDecorator != null ? this.taskDecorator.decorate(task) : task);
		try {
			if (this.blockUntilCompleted) {
				if (startTimeout != TIMEOUT_INDEFINITE || this.workListener != null) {
					obtainWorkManager().doWork(work, startTimeout, null, this.workListener);
				}
				else {
					obtainWorkManager().doWork(work);
				}
			}
			else if (this.blockUntilStarted) {
				if (startTimeout != TIMEOUT_INDEFINITE || this.workListener != null) {
					obtainWorkManager().startWork(work, startTimeout, null, this.workListener);
				}
				else {
					obtainWorkManager().startWork(work);
				}
			}
			else {
				if (startTimeout != TIMEOUT_INDEFINITE || this.workListener != null) {
					obtainWorkManager().scheduleWork(work, startTimeout, null, this.workListener);
				}
				else {
					obtainWorkManager().scheduleWork(work);
				}
			}
		}
		catch (WorkRejectedException ex) {
			if (WorkException.START_TIMED_OUT.equals(ex.getErrorCode())) {
				throw new TaskTimeoutException("JCA WorkManager rejected task because of timeout: " + task, ex);
			}
			else {
				throw new TaskRejectedException("JCA WorkManager rejected task: " + task, ex);
			}
		}
		catch (WorkException ex) {
			throw new SchedulingException("Could not schedule task on JCA WorkManager", ex);
		}
	}
