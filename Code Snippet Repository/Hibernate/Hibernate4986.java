	public static InDatabaseValueGenerationStrategyImpl create(
			SessionFactoryImplementor sessionFactoryImplementor,
			Property mappingProperty,
			ValueGeneration valueGeneration) {
		final int numberOfMappedColumns = mappingProperty.getType().getColumnSpan( sessionFactoryImplementor );
		if ( numberOfMappedColumns == 1 ) {
			return new InDatabaseValueGenerationStrategyImpl(
					valueGeneration.getGenerationTiming(),
					valueGeneration.referenceColumnInSql(),
					new String[] { valueGeneration.getDatabaseGeneratedReferencedColumnValue() }

			);
		}
		else {
			if ( valueGeneration.getDatabaseGeneratedReferencedColumnValue() != null ) {
				LOG.debugf(
						"Value generator specified column value in reference to multi-column attribute [%s -> %s]; ignoring",
						mappingProperty.getPersistentClass(),
						mappingProperty.getName()
				);
			}
			return new InDatabaseValueGenerationStrategyImpl(
					valueGeneration.getGenerationTiming(),
					valueGeneration.referenceColumnInSql(),
					new String[numberOfMappedColumns]
			);
		}
	}
