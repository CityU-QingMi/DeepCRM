	public FieldDescription[] order(FieldDescription[] persistentFields) {
		UnloadedField[] unloadedFields = new UnloadedField[persistentFields.length];
		for ( int i = 0; i < unloadedFields.length; i++ ) {
			unloadedFields[i] = new UnloadedFieldDescription( persistentFields[i] );
		}
		UnloadedField[] ordered = enhancementContext.order( unloadedFields );
		FieldDescription[] orderedFields = new FieldDescription[persistentFields.length];
		for ( int i = 0; i < orderedFields.length; i++ ) {
			orderedFields[i] = ( (UnloadedFieldDescription) ordered[i] ).fieldDescription;
		}
		return orderedFields;
	}
