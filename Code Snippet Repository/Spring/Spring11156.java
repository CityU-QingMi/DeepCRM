	public static RequestPredicate accept(MediaType... mediaTypes) {
		Assert.notEmpty(mediaTypes, "'mediaTypes' must not be empty");
		Set<MediaType> mediaTypeSet = new HashSet<>(Arrays.asList(mediaTypes));

		return headers(new Predicate<ServerRequest.Headers>() {
			@Override
			public boolean test(ServerRequest.Headers headers) {
				List<MediaType> acceptedMediaTypes = headers.accept();
				if (acceptedMediaTypes.isEmpty()) {
					acceptedMediaTypes = Collections.singletonList(MediaType.ALL);
				}
				else {
					MediaType.sortBySpecificityAndQuality(acceptedMediaTypes);
				}
				boolean match = acceptedMediaTypes.stream()
						.anyMatch(acceptedMediaType -> mediaTypeSet.stream()
								.anyMatch(acceptedMediaType::isCompatibleWith));
				traceMatch("Accept", mediaTypeSet, acceptedMediaTypes, match);
				return match;
			}
			@Override
			public String toString() {
				return String.format("Accept: %s", mediaTypeSet);
			}
		});
	}
