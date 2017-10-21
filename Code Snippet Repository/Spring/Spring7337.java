		@Nullable
		public Message<?> applyPostReceive(Message<?> message, MessageChannel channel) {
			Message<?> messageToUse = message;
			for (ChannelInterceptor interceptor : interceptors) {
				messageToUse = interceptor.postReceive(messageToUse, channel);
				if (messageToUse == null) {
					return null;
				}
			}
			return messageToUse;
		}
