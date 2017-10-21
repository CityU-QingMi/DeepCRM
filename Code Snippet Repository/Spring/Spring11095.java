	@Override
	public List<MediaType> resolveMediaTypes(ServerWebExchange exchange) throws NotAcceptableStatusException {
		String key = exchange.getRequest().getQueryParams().getFirst(getParameterName());
		if (!StringUtils.hasText(key)) {
			return Collections.emptyList();
		}
		key = formatKey(key);
		MediaType match = this.mediaTypes.get(key);
		if (match == null) {
			match = MediaTypeFactory.getMediaType("filename." + key)
					.orElseThrow(() -> {
						List<MediaType> supported = new ArrayList<>(this.mediaTypes.values());
						return new NotAcceptableStatusException(supported);
					});
		}
		this.mediaTypes.putIfAbsent(key, match);
		return Collections.singletonList(match);
	}
