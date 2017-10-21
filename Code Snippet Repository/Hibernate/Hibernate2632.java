	@Override
	public void resolveFirstChild() throws SemanticException {
		FromReferenceNode lhs = (FromReferenceNode) getFirstChild();
		SqlNode property = (SqlNode) lhs.getNextSibling();

		// Set the attributes of the property reference expression.
		String propName = property.getText();
		propertyName = propName;
		// If the uresolved property path isn't set yet, just use the property name.
		if ( propertyPath == null ) {
			propertyPath = propName;
		}
		// Resolve the LHS fully, generate implicit joins.  Pass in the property name so that the resolver can
		// discover foreign key (id) properties.
		lhs.resolve( true, true, null, this );
		setFromElement( lhs.getFromElement() );            // The 'from element' that the property is in.

		checkSubclassOrSuperclassPropertyReference( lhs, propName );
	}
