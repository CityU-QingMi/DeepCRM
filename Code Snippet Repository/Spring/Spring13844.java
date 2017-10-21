	private void checkSessionLimits() throws IOException {
		if (!shouldNotSend() && this.closeLock.tryLock()) {
			try {
				if (getTimeSinceSendStarted() > this.sendTimeLimit) {
					String format = "Message send time %d (ms) for session '%s' exceeded the allowed limit %d";
					String reason = String.format(format, getTimeSinceSendStarted(), getId(), this.sendTimeLimit);
					setLimitExceeded(reason);
				}
				else if (this.bufferSize.get() > this.bufferSizeLimit) {
					String format = "The send buffer size %d bytes for session '%s' exceeded the allowed limit %d";
					String reason = String.format(format, this.bufferSize.get(), getId(), this.bufferSizeLimit);
					setLimitExceeded(reason);
				}
			}
			finally {
				this.closeLock.unlock();
			}
		}
	}
