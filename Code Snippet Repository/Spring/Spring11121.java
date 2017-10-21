		@Override
		public Mono<Void> writeTo(ClientHttpRequest request, ExchangeStrategies strategies) {
			HttpHeaders requestHeaders = request.getHeaders();
			if (!this.headers.isEmpty()) {
				this.headers.entrySet().stream()
						.filter(entry -> !requestHeaders.containsKey(entry.getKey()))
						.forEach(entry -> requestHeaders
								.put(entry.getKey(), entry.getValue()));
			}

			MultiValueMap<String, HttpCookie> requestCookies = request.getCookies();
			if (!this.cookies.isEmpty()) {
				this.cookies.forEach((name, values) -> values.forEach(value -> {
					HttpCookie cookie = new HttpCookie(name, value);
					requestCookies.add(name, cookie);
				}));
			}

			return this.inserter.insert(request, new BodyInserter.Context() {
				@Override
				public List<HttpMessageWriter<?>> messageWriters() {
					return strategies.messageWriters();
				}

				@Override
				public Optional<ServerHttpRequest> serverRequest() {
					return Optional.empty();
				}

				@Override
				public Map<String, Object> hints() {
					return Collections.emptyMap();
				}
			});
		}
