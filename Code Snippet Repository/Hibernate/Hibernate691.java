	@Override
	public void addIdentifierGenerator(IdentifierGeneratorDefinition generator) {
		if ( generator == null || generator.getName() == null ) {
			throw new IllegalArgumentException( "ID generator object or name is null." );
		}

		if ( defaultIdentifierGeneratorNames.contains( generator.getName() ) ) {
			return;
		}

		final IdentifierGeneratorDefinition old = idGeneratorDefinitionMap.put( generator.getName(), generator );
		if ( old != null ) {
			log.duplicateGeneratorName( old.getName() );
		}
	}
