	@Override
	public WebClient build() {
		ExchangeFunction exchange = initExchangeFunction();
		ExchangeFunction filteredExchange = (this.filters != null ? this.filters.stream()
				.reduce(ExchangeFilterFunction::andThen)
				.map(filter -> filter.apply(exchange))
				.orElse(exchange) : exchange);
		return new DefaultWebClient(filteredExchange, initUriBuilderFactory(),
				unmodifiableCopy(this.defaultHeaders), unmodifiableCopy(this.defaultCookies),
				new DefaultWebClientBuilder(this));
	}
