	@Override
	@Nullable
	public CorsConfiguration getCorsConfiguration(ServerWebExchange exchange) {
		PathContainer lookupPath = exchange.getRequest().getPath().pathWithinApplication();
		return this.corsConfigurations.entrySet().stream()
				.filter(entry -> entry.getKey().matches(lookupPath))
				.map(Map.Entry::getValue)
				.findFirst()
				.orElse(null);
	}
