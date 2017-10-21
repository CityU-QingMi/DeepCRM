	@Override
	public List<MediaType> resolveMediaTypes(NativeWebRequest request) throws HttpMediaTypeNotAcceptableException {
		for (ContentNegotiationStrategy strategy : this.strategies) {
			List<MediaType> mediaTypes = strategy.resolveMediaTypes(request);
			if (mediaTypes.isEmpty() || mediaTypes.equals(MEDIA_TYPE_ALL)) {
				continue;
			}
			return mediaTypes;
		}
		return Collections.emptyList();
	}
