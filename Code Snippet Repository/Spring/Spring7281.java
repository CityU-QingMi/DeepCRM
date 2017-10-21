		@Override
		public ListenableFuture<Void> forward(Message<?> message, StompHeaderAccessor accessor) {
			try {
				ListenableFuture<Void> future = super.forward(message, accessor);
				if (message.getHeaders().get(SimpMessageHeaderAccessor.IGNORE_ERROR) == null) {
					future.get();
				}
				return future;
			}
			catch (Throwable ex) {
				throw new MessageDeliveryException(message, ex);
			}
		}
