	private ServerSentEvent<Object> buildEvent(String[] lines, ResolvableType valueType,
			Map<String, Object> hints) {

		ServerSentEvent.Builder<Object> sseBuilder = ServerSentEvent.builder();
		StringBuilder mutableData = new StringBuilder();
		StringBuilder mutableComment = new StringBuilder();

		for (String line : lines) {
			if (line.startsWith("id:")) {
				sseBuilder.id(line.substring(3));
			}
			else if (line.startsWith("event:")) {
				sseBuilder.event(line.substring(6));
			}
			else if (line.startsWith("data:")) {
				mutableData.append(line.substring(5)).append("\n");
			}
			else if (line.startsWith("retry:")) {
				sseBuilder.retry(Duration.ofMillis(Long.valueOf(line.substring(6))));
			}
			else if (line.startsWith(":")) {
				mutableComment.append(line.substring(1)).append("\n");
			}
		}
		if (mutableData.length() > 0) {
			String data = mutableData.toString();
			sseBuilder.data(decodeData(data, valueType, hints));
		}
		if (mutableComment.length() > 0) {
			String comment = mutableComment.toString();
			sseBuilder.comment(comment.substring(0, comment.length() - 1));
		}
		return sseBuilder.build();
	}
