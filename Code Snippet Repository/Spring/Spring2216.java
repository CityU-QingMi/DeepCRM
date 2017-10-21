	@Nullable
	private ResolvableType getResolvableType(ApplicationEvent event) {
		ResolvableType payloadType = null;
		if (event instanceof PayloadApplicationEvent) {
			PayloadApplicationEvent<?> payloadEvent = (PayloadApplicationEvent<?>) event;
			ResolvableType eventType = payloadEvent.getResolvableType();
			if (eventType != null) {
				payloadType = eventType.as(PayloadApplicationEvent.class).getGeneric();
			}
		}
		for (ResolvableType declaredEventType : this.declaredEventTypes) {
			Class<?> eventClass = declaredEventType.getRawClass();
			if ((eventClass == null || !ApplicationEvent.class.isAssignableFrom(eventClass)) && payloadType != null) {
				if (declaredEventType.isAssignableFrom(payloadType)) {
					return declaredEventType;
				}
			}
			if (declaredEventType.getRawClass().isInstance(event)) {
				return declaredEventType;
			}
		}
		return null;
	}
