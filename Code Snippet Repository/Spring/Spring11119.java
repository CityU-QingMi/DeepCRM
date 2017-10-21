	@Override
	public <S, P extends Publisher<S>> ClientRequest.Builder body(P publisher,
			ParameterizedTypeReference<S> typeReference) {

		Assert.notNull(publisher, "'publisher' must not be null");
		Assert.notNull(typeReference, "'typeReference' must not be null");

		this.inserter = BodyInserters.fromPublisher(publisher, typeReference);
		return this;
	}
