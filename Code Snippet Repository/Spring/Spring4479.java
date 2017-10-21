	private Object convertToByteBuffer(@Nullable Object source, TypeDescriptor sourceType) {
		byte[] bytes = (byte[]) (source instanceof byte[] ? source :
				this.conversionService.convert(source, sourceType, BYTE_ARRAY_TYPE));

		if (bytes == null) {
			return ByteBuffer.wrap(new byte[0]);
		}

		ByteBuffer byteBuffer = ByteBuffer.allocate(bytes.length);
		byteBuffer.put(bytes);

		// Extra cast necessary for compiling on JDK 9 plus running on JDK 8, since
		// otherwise the overridden ByteBuffer-returning rewind method would be chosen
		// which isn't available on JDK 8.
		return ((Buffer) byteBuffer).rewind();
	}
