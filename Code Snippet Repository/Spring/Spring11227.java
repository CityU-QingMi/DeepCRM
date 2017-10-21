		@Override
		protected boolean matchMediaType(ServerWebExchange exchange) throws UnsupportedMediaTypeStatusException {
			try {
				MediaType contentType = exchange.getRequest().getHeaders().getContentType();
				contentType = (contentType != null ? contentType : MediaType.APPLICATION_OCTET_STREAM);
				return getMediaType().includes(contentType);
			}
			catch (InvalidMediaTypeException ex) {
				throw new UnsupportedMediaTypeStatusException("Can't parse Content-Type [" +
						exchange.getRequest().getHeaders().getFirst("Content-Type") +
						"]: " + ex.getMessage());
			}
		}
