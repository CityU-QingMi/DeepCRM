	public static IdentifierValue getUnsavedIdentifierValue(
			String unsavedValue,
			Getter identifierGetter,
			Type identifierType,
			Constructor constructor) {
		if ( unsavedValue == null ) {
			if ( identifierGetter != null && constructor != null ) {
				// use the id value of a newly instantiated instance as the unsaved-value
				final Serializable defaultValue = (Serializable) identifierGetter.get( instantiate( constructor ) );
				return new IdentifierValue( defaultValue );
			}
			else if ( identifierGetter != null && (identifierType instanceof PrimitiveType) ) {
				final Serializable defaultValue = ( (PrimitiveType) identifierType ).getDefaultValue();
				return new IdentifierValue( defaultValue );
			}
			else {
				return IdentifierValue.NULL;
			}
		}
		else if ( "null".equals( unsavedValue ) ) {
			return IdentifierValue.NULL;
		}
		else if ( "undefined".equals( unsavedValue ) ) {
			return IdentifierValue.UNDEFINED;
		}
		else if ( "none".equals( unsavedValue ) ) {
			return IdentifierValue.NONE;
		}
		else if ( "any".equals( unsavedValue ) ) {
			return IdentifierValue.ANY;
		}
		else {
			try {
				return new IdentifierValue( (Serializable) ( (IdentifierType) identifierType ).stringToObject( unsavedValue ) );
			}
			catch ( ClassCastException cce ) {
				throw new MappingException( "Bad identifier type: " + identifierType.getName() );
			}
			catch ( Exception e ) {
				throw new MappingException( "Could not parse identifier unsaved-value: " + unsavedValue );
			}
		}
	}
