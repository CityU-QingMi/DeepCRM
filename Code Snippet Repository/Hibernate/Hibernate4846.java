	GenerationTarget[] buildGenerationTargets(
			TargetDescriptor targetDescriptor,
			DdlTransactionIsolator ddlTransactionIsolator,
			Map options) {
		final String scriptDelimiter = ConfigurationHelper.getString( HBM2DDL_DELIMITER, options );

		final GenerationTarget[] targets = new GenerationTarget[ targetDescriptor.getTargetTypes().size() ];

		int index = 0;

		if ( targetDescriptor.getTargetTypes().contains( TargetType.STDOUT ) ) {
			targets[index] = new GenerationTargetToStdout( scriptDelimiter );
			index++;
		}

		if ( targetDescriptor.getTargetTypes().contains( TargetType.SCRIPT ) ) {
			if ( targetDescriptor.getScriptTargetOutput() == null ) {
				throw new SchemaManagementException( "Writing to script was requested, but no script file was specified" );
			}
			targets[index] = new GenerationTargetToScript( targetDescriptor.getScriptTargetOutput(), scriptDelimiter );
			index++;
		}

		if ( targetDescriptor.getTargetTypes().contains( TargetType.DATABASE ) ) {
			targets[index] = new GenerationTargetToDatabase( ddlTransactionIsolator, false );
		}

		return targets;
	}
