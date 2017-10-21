	@Override
	public void onApplicationEvent(BrokerAvailabilityEvent event) {
		if (event.isBrokerAvailable()) {
			long delay = getRegistryExpirationPeriod() / 2;
			this.scheduledFuture = this.scheduler.scheduleWithFixedDelay(this.schedulerTask, delay);
		}
		else {
			ScheduledFuture<?> future = this.scheduledFuture;
			if (future != null ){
				future.cancel(true);
				this.scheduledFuture = null;
			}
		}
	}
