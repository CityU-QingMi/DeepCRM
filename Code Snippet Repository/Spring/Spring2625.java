	@Override
	public Date nextExecutionTime(TriggerContext triggerContext) {
		Date lastExecution = triggerContext.lastScheduledExecutionTime();
		Date lastCompletion = triggerContext.lastCompletionTime();
		if (lastExecution == null || lastCompletion == null) {
			return new Date(System.currentTimeMillis() + this.initialDelay);
		}
		if (this.fixedRate) {
			return new Date(lastExecution.getTime() + this.period);
		}
		return new Date(lastCompletion.getTime() + this.period);
	}
