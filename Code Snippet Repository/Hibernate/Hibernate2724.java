	protected void resolveCollectionProperty(AST expr) throws SemanticException {
		String propertyName = CollectionProperties.getNormalizedPropertyName( methodName );
		if ( expr instanceof FromReferenceNode ) {
			FromReferenceNode collectionNode = (FromReferenceNode) expr;
			// If this is 'elements' then create a new FROM element.
			if ( CollectionPropertyNames.COLLECTION_ELEMENTS.equals( propertyName ) ) {
				handleElements( collectionNode, propertyName );
			}
			else {
				// Not elements(x)
				fromElement = collectionNode.getFromElement();

				final CollectionPropertyReference cpr = fromElement.getCollectionPropertyReference( propertyName );
				setDataType( cpr.getType() );
				selectColumns = cpr.toColumns( fromElement.getTableAlias() );

//				setDataType( fromElement.getPropertyType( propertyName, propertyName ) );
				selectColumns = fromElement.toColumns( fromElement.getTableAlias(), propertyName, inSelect );
			}
			if ( collectionNode instanceof DotNode ) {
				prepareAnyImplicitJoins( (DotNode) collectionNode );
			}
			if ( !inSelect ) {
				fromElement.setText( "" );
				fromElement.setUseWhereFragment( false );
			}
			prepareSelectColumns( selectColumns );
			setText( selectColumns[0] );
			setType( SqlTokenTypes.SQL_TOKEN );
		}
		else {
			throw new SemanticException(
					"Unexpected expression " + expr +
							" found for collection function " + propertyName
			);
		}
	}
