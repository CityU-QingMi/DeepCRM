	private static ExchangeFilterFunction basicAuthenticationInternal(
			Function<ClientRequest, Optional<Credentials>> credentialsFunction) {

		return ExchangeFilterFunction.ofRequestProcessor(
				clientRequest -> credentialsFunction.apply(clientRequest).map(
						credentials -> {
							ClientRequest authorizedRequest = ClientRequest.from(clientRequest)
									.headers(headers -> {
										headers.set(HttpHeaders.AUTHORIZATION,
												authorization(credentials));
									})
									.build();
							return Mono.just(authorizedRequest);
						})
						.orElse(Mono.just(clientRequest)));
	}
