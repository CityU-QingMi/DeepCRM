	public Type getSelectType() {
		if ( entityType == null ) {
			return null;
		}
		boolean shallow = fromElement.getFromClause().getWalker().isShallowQuery();
		return fromElement.getSessionFactoryHelper()
				.getFactory()
				.getTypeResolver()
				.getTypeFactory().manyToOne( entityType.getAssociatedEntityName(), shallow );
	}
