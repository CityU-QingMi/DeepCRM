		@Override
		public void timerExpired(Timer timer) {
			Date actualExecutionTime = new Date();
			super.timerExpired(timer);
			Date completionTime = new Date();
			this.triggerContext.update(this.scheduledExecutionTime, actualExecutionTime, completionTime);
			if (!this.cancelled) {
				schedule();
			}
		}
