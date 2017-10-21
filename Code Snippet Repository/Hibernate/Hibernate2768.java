	public void lookupConstant(DotNode node) throws SemanticException {
		String text = ASTUtil.getPathText( node );
		Queryable persister = walker.getSessionFactoryHelper().findQueryableUsingImports( text );
		if ( persister != null ) {
			// the name of an entity class
			final String discrim = persister.getDiscriminatorSQLValue();
			node.setDataType( persister.getDiscriminatorType() );
			if ( InFragment.NULL.equals( discrim ) || InFragment.NOT_NULL.equals( discrim ) ) {
				throw new InvalidPathException(
						"subclass test not allowed for null or not null discriminator: '" + text + "'"
				);
			}
			// the class discriminator value
			setSQLValue( node, text, discrim );
		}
		else {
			Object value = ReflectHelper.getConstantValue( text, walker.getSessionFactoryHelper().getFactory() );
			if ( value == null ) {
				throw new InvalidPathException( "Invalid path: '" + text + "'" );
			}
			setConstantValue( node, text, value );
		}
	}
