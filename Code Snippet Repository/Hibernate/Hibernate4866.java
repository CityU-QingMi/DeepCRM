	private void performDrop(
			Metadata metadata,
			ExecutionOptions options,
			Dialect dialect,
			SourceDescriptor sourceDescriptor,
			GenerationTarget... targets) {
		final ImportSqlCommandExtractor commandExtractor = tool.getServiceRegistry().getService( ImportSqlCommandExtractor.class );
		final boolean format = Helper.interpretFormattingEnabled( options.getConfigurationValues() );
		final Formatter formatter = format ? FormatStyle.DDL.getFormatter() : FormatStyle.NONE.getFormatter();

		if ( sourceDescriptor.getSourceType() == SourceType.SCRIPT ) {
			dropFromScript( sourceDescriptor.getScriptSourceInput(), commandExtractor, formatter, options, targets );
		}
		else if ( sourceDescriptor.getSourceType() == SourceType.METADATA ) {
			dropFromMetadata( metadata, options, dialect, formatter, targets );
		}
		else if ( sourceDescriptor.getSourceType() == SourceType.METADATA_THEN_SCRIPT ) {
			dropFromMetadata( metadata, options, dialect, formatter, targets );
			dropFromScript( sourceDescriptor.getScriptSourceInput(), commandExtractor, formatter, options, targets );
		}
		else {
			dropFromScript( sourceDescriptor.getScriptSourceInput(), commandExtractor, formatter, options, targets );
			dropFromMetadata( metadata, options, dialect, formatter, targets );
		}
	}
