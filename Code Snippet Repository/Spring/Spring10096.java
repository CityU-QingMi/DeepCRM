	public <T> T readPojo(ObjectMapper mapper, Class<T> valueType, DataBuffer dataBuffer) {
		try {
			T value = mapper.reader().forType(valueType).readValue(DataBufferTestUtils.dumpBytes(dataBuffer));
			DataBufferUtils.release(dataBuffer);
			return value;
		}
		catch (IOException ex) {
			throw new UncheckedIOException(ex);
		}
	}
