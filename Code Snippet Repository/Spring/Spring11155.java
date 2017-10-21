	public static RequestPredicate contentType(MediaType... mediaTypes) {
		Assert.notEmpty(mediaTypes, "'mediaTypes' must not be empty");
		Set<MediaType> mediaTypeSet = new HashSet<>(Arrays.asList(mediaTypes));

		return headers(new Predicate<ServerRequest.Headers>() {
			@Override
			public boolean test(ServerRequest.Headers headers) {
				MediaType contentType =
						headers.contentType().orElse(MediaType.APPLICATION_OCTET_STREAM);
				boolean match = mediaTypeSet.stream()
						.anyMatch(mediaType -> mediaType.includes(contentType));
				traceMatch("Content-Type", mediaTypeSet, contentType, match);
				return match;
			}

			@Override
			public String toString() {
				return String.format("Content-Type: %s", mediaTypeSet);
			}
		});
	}
