		private <T extends Publisher<?>> T bodyToPublisher(ClientResponse response,
				BodyExtractor<T, ? super ClientHttpResponse> extractor,
				Function<Mono<? extends Throwable>, T> errorFunction) {

			return this.statusHandlers.stream()
					.filter(statusHandler -> statusHandler.test(response.statusCode()))
					.findFirst()
					.map(statusHandler -> statusHandler.apply(response))
					.map(errorFunction::apply)
					.orElse(response.body(extractor));
		}
