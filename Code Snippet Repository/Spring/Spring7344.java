		@Nullable
		private Message<?> applyBeforeHandle(Message<?> message) {
			Message<?> messageToUse = message;
			for (ExecutorChannelInterceptor interceptor : executorInterceptors) {
				messageToUse = interceptor.beforeHandle(messageToUse, ExecutorSubscribableChannel.this, this.messageHandler);
				if (messageToUse == null) {
					String name = interceptor.getClass().getSimpleName();
					if (logger.isDebugEnabled()) {
						logger.debug(name + " returned null from beforeHandle, i.e. precluding the send.");
					}
					triggerAfterMessageHandled(message, null);
					return null;
				}
				this.interceptorIndex++;
			}
			return messageToUse;
		}
