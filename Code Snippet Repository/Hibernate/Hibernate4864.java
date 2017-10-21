	@Override
	public void doDrop(
			Metadata metadata,
			ExecutionOptions options,
			SourceDescriptor sourceDescriptor,
			TargetDescriptor targetDescriptor) {

		if ( targetDescriptor.getTargetTypes().isEmpty() ) {
			return;
		}

		final JdbcContext jdbcContext = tool.resolveJdbcContext( options.getConfigurationValues() );
		final GenerationTarget[] targets = tool.buildGenerationTargets( targetDescriptor, jdbcContext, options.getConfigurationValues(), true );

		doDrop( metadata, options, jdbcContext.getDialect(), sourceDescriptor, targets );
	}
