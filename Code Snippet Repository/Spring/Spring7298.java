	private void writeHeaders(StompCommand command, Map<String, Object> headers, byte[] payload,
			DataOutputStream output) throws IOException {

		@SuppressWarnings("unchecked")
		Map<String,List<String>> nativeHeaders =
				(Map<String, List<String>>) headers.get(NativeMessageHeaderAccessor.NATIVE_HEADERS);

		if (logger.isTraceEnabled()) {
			logger.trace("Encoding STOMP " + command + ", headers=" + nativeHeaders);
		}

		if (nativeHeaders == null) {
			return;
		}

		boolean shouldEscape = (command != StompCommand.CONNECT && command != StompCommand.CONNECTED);

		for (Entry<String, List<String>> entry : nativeHeaders.entrySet()) {
			if (command.requiresContentLength() && "content-length".equals(entry.getKey())) {
				continue;
			}

			List<String> values = entry.getValue();
			if (StompCommand.CONNECT.equals(command) &&
					StompHeaderAccessor.STOMP_PASSCODE_HEADER.equals(entry.getKey())) {
				values = Collections.singletonList(StompHeaderAccessor.getPasscode(headers));
			}

			byte[] encodedKey = encodeHeaderKey(entry.getKey(), shouldEscape);
			for (String value : values) {
				output.write(encodedKey);
				output.write(COLON);
				output.write(encodeHeaderValue(value, shouldEscape));
				output.write(LF);
			}
		}

		if (command.requiresContentLength()) {
			int contentLength = payload.length;
			output.write("content-length:".getBytes(StandardCharsets.UTF_8));
			output.write(Integer.toString(contentLength).getBytes(StandardCharsets.UTF_8));
			output.write(LF);
		}
	}
