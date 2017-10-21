	private boolean detectImmutableMessageInterceptor(MessageChannel channel) {
		if (this.immutableMessageInterceptorPresent != null) {
			return this.immutableMessageInterceptorPresent;
		}

		if (channel instanceof AbstractMessageChannel) {
			for (ChannelInterceptor interceptor : ((AbstractMessageChannel) channel).getInterceptors()) {
				if (interceptor instanceof ImmutableMessageChannelInterceptor) {
					this.immutableMessageInterceptorPresent = true;
					return true;
				}
			}
		}
		this.immutableMessageInterceptorPresent = false;
		return false;
	}
