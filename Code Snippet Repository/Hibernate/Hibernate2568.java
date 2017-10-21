	@Override
	protected void validateMapPropertyExpression(AST node) throws SemanticException {
		try {
			FromReferenceNode fromReferenceNode = (FromReferenceNode) node;
			QueryableCollection collectionPersister = fromReferenceNode.getFromElement().getQueryableCollection();
			if ( !Map.class.isAssignableFrom( collectionPersister.getCollectionType().getReturnedClass() ) ) {
				throw new SemanticException( "node did not reference a map" );
			}
		}
		catch (SemanticException se) {
			throw se;
		}
		catch (Throwable t) {
			throw new SemanticException( "node did not reference a map" );
		}
	}
