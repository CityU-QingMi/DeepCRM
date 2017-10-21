		@Override
		protected boolean matchInternal(StompHeaderAccessor headers, Object payload) {
			if (!this.subscriptionId.equals(headers.getSubscriptionId()) ||  !this.destination.equals(headers.getDestination())) {
				return false;
			}
			if (payload instanceof byte[] && this.payload instanceof byte[]) {
				return Arrays.equals((byte[]) payload, (byte[]) this.payload);
			}
			else {
				return this.payload.equals(payload);
			}
		}
