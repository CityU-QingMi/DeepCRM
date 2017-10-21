	public List<Message<byte[]>> decode(ByteBuffer newBuffer) {
		this.chunks.add(newBuffer);
		checkBufferLimits();

		Integer contentLength = this.expectedContentLength;
		if (contentLength != null && getBufferSize() < contentLength) {
			return Collections.emptyList();
		}

		ByteBuffer bufferToDecode = assembleChunksAndReset();
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		List<Message<byte[]>> messages = this.stompDecoder.decode(bufferToDecode, headers);

		if (bufferToDecode.hasRemaining()) {
			this.chunks.add(bufferToDecode);
			this.expectedContentLength = StompHeaderAccessor.getContentLength(headers);
		}

		return messages;
	}
