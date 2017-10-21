	private void dereferenceEntityIdentifier(String propertyName, DotNode dotParent) {
		// special shortcut for id properties, skip the join!
		// this must only occur at the _end_ of a path expression
		if ( LOG.isDebugEnabled() ) {
			LOG.debugf(
					"dereferenceShortcut() : property %s in %s does not require a join.",
					propertyName,
					getFromElement().getClassName()
			);
		}

		initText();
		setPropertyNameAndPath( dotParent ); // Set the unresolved path in this node and the parent.
		// Set the text for the parent.
		if ( dotParent != null ) {
			dotParent.dereferenceType = DereferenceType.IDENTIFIER;
			dotParent.setText( getText() );
			dotParent.columns = getColumns();
		}
	}
