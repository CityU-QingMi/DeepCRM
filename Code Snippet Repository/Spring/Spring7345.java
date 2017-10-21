		private void triggerAfterMessageHandled(Message<?> message, @Nullable Exception ex) {
			for (int i = this.interceptorIndex; i >= 0; i--) {
				ExecutorChannelInterceptor interceptor = executorInterceptors.get(i);
				try {
					interceptor.afterMessageHandled(message, ExecutorSubscribableChannel.this, this.messageHandler, ex);
				}
				catch (Throwable ex2) {
					logger.error("Exception from afterMessageHandled in " + interceptor, ex2);
				}
			}
		}
