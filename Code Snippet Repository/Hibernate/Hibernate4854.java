	private void performCreation(
			Metadata metadata,
			Dialect dialect,
			ExecutionOptions options,
			SourceDescriptor sourceDescriptor,
			GenerationTarget... targets) {
		final ImportSqlCommandExtractor commandExtractor = tool.getServiceRegistry().getService( ImportSqlCommandExtractor.class );

		final boolean format = Helper.interpretFormattingEnabled( options.getConfigurationValues() );
		final Formatter formatter = format ? FormatStyle.DDL.getFormatter() : FormatStyle.NONE.getFormatter();

		switch ( sourceDescriptor.getSourceType() ) {
			case SCRIPT: {
				createFromScript( sourceDescriptor.getScriptSourceInput(), commandExtractor, formatter, options, targets );
				break;
			}
			case METADATA: {
				createFromMetadata( metadata, options, dialect, formatter, targets );
				break;
			}
			case METADATA_THEN_SCRIPT: {
				createFromMetadata( metadata, options, dialect, formatter, targets );
				createFromScript( sourceDescriptor.getScriptSourceInput(), commandExtractor, formatter, options, targets );
				break;
			}
			case SCRIPT_THEN_METADATA: {
				createFromScript( sourceDescriptor.getScriptSourceInput(), commandExtractor, formatter, options, targets );
				createFromMetadata( metadata, options, dialect, formatter, targets );
			}
		}

		applyImportSources( options, commandExtractor, format, targets );
	}
