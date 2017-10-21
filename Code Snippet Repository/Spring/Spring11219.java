	public final boolean match(ServerWebExchange exchange) {
		boolean isMatch;
		if (this.value != null) {
			isMatch = matchValue(exchange);
		}
		else {
			isMatch = matchName(exchange);
		}
		return this.isNegated != isMatch;
	}
