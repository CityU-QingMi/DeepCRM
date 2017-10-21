	@Override
	public void start() throws JmsException {
		if (!this.initializationInvoked) {
			throw new IllegalStateException("afterPropertiesSet should have been invoked before start on " + this);
		}
		if (this.startInvoked) {
			throw new IllegalStateException("Start already invoked on " + this);
		}
		this.startInvoked = true;
	}
