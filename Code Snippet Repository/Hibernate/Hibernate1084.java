	public CtField[] order(CtField[] persistentFields) {
		UnloadedField[] unloadedFields = new UnloadedField[persistentFields.length];
		for ( int i = 0; i < unloadedFields.length; i++ ) {
			unloadedFields[i] = new UnloadedCtField( persistentFields[i] );
		}
		UnloadedField[] ordered = enhancementContext.order( unloadedFields );
		CtField[] orderedFields = new CtField[persistentFields.length];
		for ( int i = 0; i < orderedFields.length; i++ ) {
			orderedFields[i] = ( (UnloadedCtField) ordered[i] ).ctField;
		}
		return orderedFields;
	}
