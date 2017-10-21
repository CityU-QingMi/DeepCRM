	private void publishEvent(ApplicationEventPublisher publisher, ApplicationEvent event) {
		try {
			publisher.publishEvent(event);
		}
		catch (Throwable ex) {
			if (logger.isErrorEnabled()) {
				logger.error("Error publishing " + event, ex);
			}
		}
	}
