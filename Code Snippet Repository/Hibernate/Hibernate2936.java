	protected void buildSequence(Database database) {
		final int sourceIncrementSize = getSourceIncrementSize();

		final Namespace namespace = database.locateNamespace(
				logicalQualifiedSequenceName.getCatalogName(),
				logicalQualifiedSequenceName.getSchemaName()
		);
		Sequence sequence = namespace.locateSequence( logicalQualifiedSequenceName.getObjectName() );
		if ( sequence != null ) {
			sequence.validate( initialValue, sourceIncrementSize );
		}
		else {
			sequence = namespace.createSequence( logicalQualifiedSequenceName.getObjectName(), initialValue, sourceIncrementSize );
		}

		this.sequenceName = database.getJdbcEnvironment().getQualifiedObjectNameFormatter().format(
				sequence.getName(),
				database.getJdbcEnvironment().getDialect()
		);
	}
