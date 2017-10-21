	public static ExchangeFilterFunction statusError(Predicate<HttpStatus> statusPredicate,
			Function<ClientResponse, ? extends Throwable> exceptionFunction) {

		Assert.notNull(statusPredicate, "'statusPredicate' must not be null");
		Assert.notNull(exceptionFunction, "'exceptionFunction' must not be null");

		return ExchangeFilterFunction.ofResponseProcessor(
				clientResponse -> {
					if (statusPredicate.test(clientResponse.statusCode())) {
						return Mono.error(exceptionFunction.apply(clientResponse));
					}
					else {
						return Mono.just(clientResponse);
					}
				}
		);
	}
