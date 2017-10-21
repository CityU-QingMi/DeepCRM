	@Nullable
	protected Object[] resolveArguments(ApplicationEvent event) {
		ResolvableType declaredEventType = getResolvableType(event);
		if (declaredEventType == null) {
			return null;
		}
		if (this.method.getParameterCount() == 0) {
			return new Object[0];
		}
		Class<?> eventClass = declaredEventType.getRawClass();
		if ((eventClass == null || !ApplicationEvent.class.isAssignableFrom(eventClass)) &&
				event instanceof PayloadApplicationEvent) {
			return new Object[] {((PayloadApplicationEvent) event).getPayload()};
		}
		else {
			return new Object[] {event};
		}
	}
