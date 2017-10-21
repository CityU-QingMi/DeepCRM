		@Override
		public TypedValue read(EvaluationContext context, @Nullable Object target, String name) throws AccessException {
			Assert.state(target instanceof MessageHeaders, "No MessageHeaders");
			MessageHeaders headers = (MessageHeaders) target;
			SimpMessageHeaderAccessor accessor =
					MessageHeaderAccessor.getAccessor(headers, SimpMessageHeaderAccessor.class);
			Assert.state(accessor != null, "No SimpMessageHeaderAccessor");
			Object value;
			if ("destination".equalsIgnoreCase(name)) {
				value = accessor.getDestination();
			}
			else {
				value = accessor.getFirstNativeHeader(name);
				if (value == null) {
					value = headers.get(name);
				}
			}
			return new TypedValue(value);
		}
