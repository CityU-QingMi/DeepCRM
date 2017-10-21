		@Override
		protected void send(Object element) throws IOException {
			if (element instanceof ServerSentEvent) {
				ServerSentEvent<?> event = (ServerSentEvent<?>) element;
				((SseEmitter) getEmitter()).send(adapt(event));
			}
			else {
				getEmitter().send(element, MediaType.APPLICATION_JSON);
			}
		}
