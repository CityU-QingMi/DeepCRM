	private void setFKNameIfDefined(Join join) {
		// just awful..

		org.hibernate.annotations.Table matchingTable = findMatchingComplimentTableAnnotation( join );
		if ( matchingTable != null && !BinderHelper.isEmptyAnnotationValue( matchingTable.foreignKey().name() ) ) {
			( (SimpleValue) join.getKey() ).setForeignKeyName( matchingTable.foreignKey().name() );
		}
		else {
			javax.persistence.SecondaryTable jpaSecondaryTable = findMatchingSecondaryTable( join );
			if ( jpaSecondaryTable != null ) {
				if ( jpaSecondaryTable.foreignKey().value() == ConstraintMode.NO_CONSTRAINT ) {
					( (SimpleValue) join.getKey() ).setForeignKeyName( "none" );
				}
				else {
					( (SimpleValue) join.getKey() ).setForeignKeyName( StringHelper.nullIfEmpty( jpaSecondaryTable.foreignKey().name() ) );
					( (SimpleValue) join.getKey() ).setForeignKeyDefinition( StringHelper.nullIfEmpty( jpaSecondaryTable.foreignKey().foreignKeyDefinition() ) );
				}
			}
		}
	}
