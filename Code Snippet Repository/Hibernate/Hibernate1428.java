	public static void linkJoinColumnWithValueOverridingNameIfImplicit(
			PersistentClass referencedEntity,
			Iterator columnIterator,
			Ejb3JoinColumn[] columns,
			SimpleValue value) {
		for (Ejb3JoinColumn joinCol : columns) {
			Column synthCol = (Column) columnIterator.next();
			if ( joinCol.isNameDeferred() ) {
				//this has to be the default value
				joinCol.linkValueUsingDefaultColumnNaming( synthCol, referencedEntity, value );
			}
			else {
				joinCol.linkWithValue( value );
				joinCol.overrideFromReferencedColumnIfNecessary( synthCol );
			}
		}
	}
