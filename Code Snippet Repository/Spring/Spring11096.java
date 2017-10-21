	public RequestedContentTypeResolver build() {

		List<RequestedContentTypeResolver> resolvers =
				this.candidates.isEmpty() ?
						Collections.singletonList(new HeaderContentTypeResolver()) :
						this.candidates.stream().map(Supplier::get).collect(Collectors.toList());

		return exchange -> {
			for (RequestedContentTypeResolver resolver : resolvers) {
				List<MediaType> type = resolver.resolveMediaTypes(exchange);
				if (type.isEmpty() || (type.size() == 1 && type.contains(MediaType.ALL))) {
					continue;
				}
				return type;
			}
			return Collections.emptyList();
		};
	}
