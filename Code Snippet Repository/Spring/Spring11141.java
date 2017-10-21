		@Override
		public Mono<Void> writeTo(ServerWebExchange exchange, Context context) {
			ServerHttpResponse response = exchange.getResponse();
			writeStatusAndHeaders(response);
			MediaType contentType = exchange.getResponse().getHeaders().getContentType();
			Locale locale = LocaleContextHolder.getLocale(exchange.getLocaleContext());
			Stream<ViewResolver> viewResolverStream = context.viewResolvers().stream();

			return Flux.fromStream(viewResolverStream)
					.concatMap(viewResolver -> viewResolver.resolveViewName(name(), locale))
					.next()
					.switchIfEmpty(Mono.error(new IllegalArgumentException("Could not resolve view with name '" +
							name() +"'")))
					.flatMap(view -> view.render(model(), contentType, exchange));
		}
