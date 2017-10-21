	@Override
	public MetadataBuilder enableNewIdentifierGeneratorSupport(boolean enabled) {
		if ( enabled ) {
			this.options.idGenerationTypeInterpreter.disableLegacyFallback();
		}
		else {
			this.options.idGenerationTypeInterpreter.enableLegacyFallback();
		}
		return this;
	}
