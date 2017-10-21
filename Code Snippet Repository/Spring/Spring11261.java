	protected AbstractMessageReaderArgumentResolver(List<HttpMessageReader<?>> messageReaders,
			ReactiveAdapterRegistry adapterRegistry) {

		super(adapterRegistry);
		Assert.notEmpty(messageReaders, "At least one HttpMessageReader is required");
		Assert.notNull(adapterRegistry, "ReactiveAdapterRegistry is required");
		this.messageReaders = messageReaders;
		this.supportedMediaTypes = messageReaders.stream()
				.flatMap(converter -> converter.getReadableMediaTypes().stream())
				.collect(Collectors.toList());
	}
