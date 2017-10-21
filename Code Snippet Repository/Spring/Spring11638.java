		@Bean
		public SimpleUrlHandlerMapping handlerMapping() {
			return new SimpleUrlHandlerMapping() {
				{
					Map<String, Object> map = new HashMap<>();
					map.put("/foo", (WebHandler) exchange ->
							exchange.getResponse().writeWith(Flux.just(asDataBuffer("foo"))));
					map.put("/bar", (WebHandler) exchange ->
							exchange.getResponse().writeWith(Flux.just(asDataBuffer("bar"))));
					map.put("/header", (WebHandler) exchange -> {
						exchange.getResponse().getHeaders().add("foo", "bar");
						return Mono.empty();
					});
					setUrlMap(map);
				}
			};
		}
