	private void collectionProperty(AST path, AST name) throws SemanticException {
		if ( path == null ) {
			throw new SemanticException( "Collection function " + name.getText() + " has no path!" );
		}

		SqlNode expr = (SqlNode) path;
		Type type = expr.getDataType();
		LOG.debugf( "collectionProperty() :  name=%s type=%s", name, type );

		resolveCollectionProperty( expr );
	}
