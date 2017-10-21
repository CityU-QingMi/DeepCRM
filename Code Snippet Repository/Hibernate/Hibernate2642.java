	void registerFromElement(FromElement element) {
		fromElements.add( element );
		String classAlias = element.getClassAlias();
		if ( classAlias != null ) {
			// The HQL class alias refers to the class name.
			fromElementByClassAlias.put( classAlias, element );
		}
		// Associate the table alias with the element.
		String tableAlias = element.getTableAlias();
		if ( tableAlias != null ) {
			fromElementByTableAlias.put( tableAlias, element );
		}

		if ( element instanceof EntityJoinFromElement ) {
			if ( entityJoinFromElements == null ) {
				entityJoinFromElements = new ArrayList<EntityJoinFromElement>();
			}
			entityJoinFromElements.add( (EntityJoinFromElement) element );
		}
	}
