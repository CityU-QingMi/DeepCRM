	@Override
	public List<MediaType> resolveMediaTypes(ServerWebExchange exchange) throws NotAcceptableStatusException {
		try {
			List<MediaType> mediaTypes = exchange.getRequest().getHeaders().getAccept();
			MediaType.sortBySpecificityAndQuality(mediaTypes);
			return mediaTypes;
		}
		catch (InvalidMediaTypeException ex) {
			String value = exchange.getRequest().getHeaders().getFirst("Accept");
			throw new NotAcceptableStatusException(
					"Could not parse 'Accept' header [" + value + "]: " + ex.getMessage());
		}
	}
