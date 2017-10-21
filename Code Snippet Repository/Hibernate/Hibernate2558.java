	@Override
	protected void beforeSelectClause() throws SemanticException {
		// Turn off includeSubclasses on all FromElements.
		FromClause from = getCurrentFromClause();
		List fromElements = from.getFromElements();
		for ( Iterator iterator = fromElements.iterator(); iterator.hasNext(); ) {
			FromElement fromElement = (FromElement) iterator.next();
			fromElement.setIncludeSubclasses( false );
		}
	}
