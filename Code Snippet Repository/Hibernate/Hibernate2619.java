	public void resolve(boolean inSelect) {
		this.dialectCastFunction = getSessionFactoryHelper().findSQLFunction( "cast" );
		if ( dialectCastFunction == null ) {
			dialectCastFunction = CastFunction.INSTANCE;
		}

		this.expressionNode = (Node) getFirstChild();
		if ( expressionNode == null ) {
			throw new QueryException( "Could not resolve expression to CAST" );
		}
		if ( SqlNode.class.isInstance( expressionNode ) ) {
			final Type expressionType = ( (SqlNode) expressionNode ).getDataType();
			if ( expressionType != null ) {
				if ( expressionType.isEntityType() ) {
					throw new QueryException( "Expression to CAST cannot be an entity : " + expressionNode.getText() );
				}
				if ( expressionType.isComponentType() ) {
					throw new QueryException( "Expression to CAST cannot be a composite : " + expressionNode.getText() );
				}
				if ( expressionType.isCollectionType() ) {
					throw new QueryException( "Expression to CAST cannot be a collection : " + expressionNode.getText() );
				}
			}
		}

		this.typeNode = (IdentNode) expressionNode.getNextSibling();
		if ( typeNode == null ) {
			throw new QueryException( "Could not resolve requested type for CAST" );
		}

		final String typeName = typeNode.getText();
		this.castType = getSessionFactoryHelper().getFactory().getTypeResolver().heuristicType( typeName );
		if ( castType == null ) {
			throw new QueryException( "Could not resolve requested type for CAST : " + typeName );
		}
		if ( castType.isEntityType() ) {
			throw new QueryException( "CAST target type cannot be an entity : " + expressionNode.getText() );
		}
		if ( castType.isComponentType() ) {
			throw new QueryException( "CAST target type cannot be a composite : " + expressionNode.getText() );
		}
		if ( castType.isCollectionType() ) {
			throw new QueryException( "CAST target type cannot be a collection : " + expressionNode.getText() );
		}
		setDataType( castType );
	}
