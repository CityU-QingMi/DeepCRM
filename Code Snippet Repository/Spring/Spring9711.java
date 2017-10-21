	@Override
	public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {

		if (exchange.getRequest().getMethod() != HttpMethod.POST) {
			return chain.filter(exchange);
		}

		return exchange.getFormData()
				.map(formData -> {
					String method = formData.getFirst(this.methodParamName);
					return StringUtils.hasLength(method) ? mapExchange(exchange, method) : exchange;
				})
				.flatMap(chain::filter);
	}
