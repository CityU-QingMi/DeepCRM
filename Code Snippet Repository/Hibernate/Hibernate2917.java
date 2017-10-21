	@Override
	public void registerExportables(Database database) {
		final Namespace namespace = database.locateNamespace(
				logicalQualifiedSequenceName.getCatalogName(),
				logicalQualifiedSequenceName.getSchemaName()
		);
		Sequence sequence = namespace.locateSequence( logicalQualifiedSequenceName.getObjectName() );
		if ( sequence != null ) {
			sequence.validate( 1, 1 );
		}
		else {
			sequence = namespace.createSequence(
					logicalQualifiedSequenceName.getObjectName(),
					1,
					1
			);
		}

		final JdbcEnvironment jdbcEnvironment = database.getJdbcEnvironment();
		final Dialect dialect = jdbcEnvironment.getDialect();

		this.sequenceName = jdbcEnvironment.getQualifiedObjectNameFormatter().format(
				sequence.getName(),
				dialect
		);
		this.sql = jdbcEnvironment.getDialect().getSequenceNextValString( sequenceName );
	}
