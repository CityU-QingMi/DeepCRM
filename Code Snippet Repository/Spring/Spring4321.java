	public ReactiveAdapter(ReactiveTypeDescriptor descriptor,
			Function<Object, Publisher<?>> toPublisherFunction,
			Function<Publisher<?>, Object> fromPublisherFunction) {

		Assert.notNull(descriptor, "'descriptor' is required");
		Assert.notNull(toPublisherFunction, "'toPublisherFunction' is required");
		Assert.notNull(fromPublisherFunction, "'fromPublisherFunction' is required");

		this.descriptor = descriptor;
		this.toPublisherFunction = toPublisherFunction;
		this.fromPublisherFunction = fromPublisherFunction;
	}
