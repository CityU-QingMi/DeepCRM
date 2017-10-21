		private SseEmitter.SseEventBuilder adapt(ServerSentEvent<?> sse) {
			SseEmitter.SseEventBuilder builder = SseEmitter.event();
			String id = sse.id();
			String event = sse.event();
			Duration retry = sse.retry();
			String comment = sse.comment();
			Object data = sse.data();
			if (id != null) {
				builder.id(id);
			}
			if (event != null) {
				builder.name(event);
			}
			if (data != null) {
				builder.data(data);
			}
			if (retry != null) {
				builder.reconnectTime(retry.toMillis());
			}
			if (comment != null) {
				builder.comment(comment);
			}
			return builder;
		}
