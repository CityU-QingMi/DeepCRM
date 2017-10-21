	public final boolean match(ServerWebExchange exchange) {
		try {
			boolean match = matchMediaType(exchange);
			return (!this.isNegated == match);
		}
		catch (NotAcceptableStatusException ex) {
			return false;
		}
		catch (UnsupportedMediaTypeStatusException ex) {
			return false;
		}
	}
