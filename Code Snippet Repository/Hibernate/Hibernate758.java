	public IdentifierGeneratorDefinition(
			final String name,
			final String strategy,
			final Map<String, String> parameters) {
		this.name = name;
		this.strategy = strategy;
		if ( CollectionHelper.isEmpty( parameters ) ) {
			this.parameters = Collections.emptyMap();
		}
		else {
			this.parameters = Collections.unmodifiableMap( parameters );
		}
	}
