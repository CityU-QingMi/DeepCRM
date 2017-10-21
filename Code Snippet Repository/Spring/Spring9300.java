	private static Flux<DataBuffer> splitOnNewline(DataBuffer dataBuffer) {
		List<DataBuffer> results = new ArrayList<>();
		int startIdx = 0;
		int endIdx;
		final int limit = dataBuffer.readableByteCount();
		do {
			endIdx = dataBuffer.indexOf(NEWLINE_DELIMITER, startIdx);
			int length = endIdx != -1 ? endIdx - startIdx + 1 : limit - startIdx;
			DataBuffer token = dataBuffer.slice(startIdx, length);
			results.add(DataBufferUtils.retain(token));
			startIdx = endIdx + 1;
		}
		while (startIdx < limit && endIdx != -1);
		DataBufferUtils.release(dataBuffer);
		return Flux.fromIterable(results);
	}
