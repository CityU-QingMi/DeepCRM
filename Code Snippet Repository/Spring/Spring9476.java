	private boolean isWritePossible() {
		if (this.responseChannel == null) {
			this.responseChannel = this.exchange.getResponseChannel();
		}
		if (this.responseChannel.isWriteResumed()) {
			return true;
		} else {
			this.responseChannel.resumeWrites();
			return false;
		}
	}
