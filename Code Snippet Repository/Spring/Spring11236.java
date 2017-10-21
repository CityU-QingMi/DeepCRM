		@Override
		protected boolean matchMediaType(ServerWebExchange exchange) throws NotAcceptableStatusException {
			List<MediaType> acceptedMediaTypes = getAcceptedMediaTypes(exchange);
			for (MediaType acceptedMediaType : acceptedMediaTypes) {
				if (getMediaType().isCompatibleWith(acceptedMediaType)) {
					return true;
				}
			}
			return false;
		}
