	public List<Message<byte[]>> decode(ByteBuffer byteBuffer, @Nullable MultiValueMap<String, String> partialMessageHeaders) {
		List<Message<byte[]>> messages = new ArrayList<>();
		while (byteBuffer.hasRemaining()) {
			Message<byte[]> message = decodeMessage(byteBuffer, partialMessageHeaders);
			if (message != null) {
				messages.add(message);
			}
			else {
				break;
			}
		}
		return messages;
	}
