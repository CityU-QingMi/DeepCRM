	@Override
	protected void doShutdown() throws JMSException {
		logger.debug("Waiting for shutdown of message listener invokers");
		try {
			synchronized (this.lifecycleMonitor) {
				// Waiting for AsyncMessageListenerInvokers to deactivate themselves...
				while (this.activeInvokerCount > 0) {
					if (logger.isDebugEnabled()) {
						logger.debug("Still waiting for shutdown of " + this.activeInvokerCount +
								" message listener invokers");
					}
					long timeout = getReceiveTimeout();
					if (timeout > 0) {
						this.lifecycleMonitor.wait(timeout);
					}
					else {
						this.lifecycleMonitor.wait();
					}
				}
				// Clear remaining scheduled invokers, possibly left over as paused tasks...
				for (AsyncMessageListenerInvoker scheduledInvoker : this.scheduledInvokers) {
					scheduledInvoker.clearResources();
				}
				this.scheduledInvokers.clear();
			}
		}
		catch (InterruptedException ex) {
			// Re-interrupt current thread, to allow other threads to react.
			Thread.currentThread().interrupt();
		}
	}
