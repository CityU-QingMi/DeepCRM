	private static Optional<Mono<Void>> zeroCopy(Resource resource, @Nullable ResourceRegion region,
			ReactiveHttpOutputMessage message) {

		if (message instanceof ZeroCopyHttpOutputMessage) {
			if (resource.isFile()) {
				try {
					File file = resource.getFile();
					long pos = region != null ? region.getPosition() : 0;
					long count = region != null ? region.getCount() : file.length();
					return Optional.of(((ZeroCopyHttpOutputMessage) message).writeWith(file, pos, count));
				}
				catch (IOException ex) {
					// should not happen
				}
			}
		}
		return Optional.empty();
	}
