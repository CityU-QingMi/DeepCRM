	public static Flux<DataBuffer> read(Resource resource, long position,
			DataBufferFactory dataBufferFactory, int bufferSize) {

		try {
			if (resource.isFile()) {
				File file = resource.getFile();
				AsynchronousFileChannel channel =
						AsynchronousFileChannel.open(file.toPath(), StandardOpenOption.READ);
				return DataBufferUtils.read(channel, position, dataBufferFactory, bufferSize);
			}
		}
		catch (IOException ignore) {
			// fallback to resource.readableChannel(), below
		}

		try {
			ReadableByteChannel channel = resource.readableChannel();
			Flux<DataBuffer> in = DataBufferUtils.read(channel, dataBufferFactory, bufferSize);
			return DataBufferUtils.skipUntilByteCount(in, position);
		}
		catch (IOException ex) {
			return Flux.error(ex);
		}
	}
