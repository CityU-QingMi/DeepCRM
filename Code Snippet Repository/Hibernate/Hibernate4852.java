	@Override
	public void doCreation(
			Metadata metadata,
			ExecutionOptions options,
			SourceDescriptor sourceDescriptor,
			TargetDescriptor targetDescriptor) {
		if ( targetDescriptor.getTargetTypes().isEmpty() ) {
			return;
		}

		final JdbcContext jdbcContext = tool.resolveJdbcContext( options.getConfigurationValues() );
		final GenerationTarget[] targets = tool.buildGenerationTargets(
				targetDescriptor,
				jdbcContext,
				options.getConfigurationValues(),
				true
		);

		doCreation( metadata, jdbcContext.getDialect(), options, sourceDescriptor, targets );
	}
