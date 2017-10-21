	private static Ejb3DiscriminatorColumn processSingleTableDiscriminatorProperties(
			XClass clazzToProcess,
			MetadataBuildingContext context,
			InheritanceState inheritanceState,
			EntityBinder entityBinder) {
		final boolean isRoot = !inheritanceState.hasParents();

		Ejb3DiscriminatorColumn discriminatorColumn = null;
		javax.persistence.DiscriminatorColumn discAnn = clazzToProcess.getAnnotation(
				javax.persistence.DiscriminatorColumn.class
		);
		DiscriminatorType discriminatorType = discAnn != null
				? discAnn.discriminatorType()
				: DiscriminatorType.STRING;

		org.hibernate.annotations.DiscriminatorFormula discFormulaAnn = clazzToProcess.getAnnotation(
				org.hibernate.annotations.DiscriminatorFormula.class
		);
		if ( isRoot ) {
			discriminatorColumn = Ejb3DiscriminatorColumn.buildDiscriminatorColumn(
					discriminatorType,
					discAnn,
					discFormulaAnn,
					context
			);
		}
		if ( discAnn != null && !isRoot ) {
			LOG.invalidDiscriminatorAnnotation( clazzToProcess.getName() );
		}

		final String discriminatorValue = clazzToProcess.isAnnotationPresent( DiscriminatorValue.class )
				? clazzToProcess.getAnnotation( DiscriminatorValue.class ).value()
				: null;
		entityBinder.setDiscriminatorValue( discriminatorValue );

		DiscriminatorOptions discriminatorOptions = clazzToProcess.getAnnotation( DiscriminatorOptions.class );
		if ( discriminatorOptions != null) {
			entityBinder.setForceDiscriminator( discriminatorOptions.force() );
			entityBinder.setInsertableDiscriminator( discriminatorOptions.insert() );
		}

		return discriminatorColumn;
	}
