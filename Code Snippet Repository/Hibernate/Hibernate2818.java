	private String getElementName(PathExpressionParser.CollectionElement element, QueryTranslatorImpl q) throws QueryException {
		String name;
		if ( element.isOneToMany ) {
			name = element.alias;
		}
		else {
			Type type = element.elementType;
			if ( type.isEntityType() ) { //ie. a many-to-many
				String entityName = ( ( EntityType ) type ).getAssociatedEntityName();
				name = pathExpressionParser.continueFromManyToMany( entityName, element.elementColumns, q );
			}
			else {
				throw new QueryException( "illegally dereferenced collection element" );
			}
		}
		return name;
	}
