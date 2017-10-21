		@Nullable
		public ScheduledFuture<?> schedule() {
			Date nextExecutionTime = this.trigger.nextExecutionTime(this.triggerContext);
			if (nextExecutionTime == null) {
				return null;
			}
			this.scheduledExecutionTime = nextExecutionTime;
			setTimer(obtainTimerManager().schedule(this, this.scheduledExecutionTime));
			return this;
		}
