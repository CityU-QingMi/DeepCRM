	public void resolveSelectExpression() throws SemanticException {
		if ( getWalker().isShallowQuery() || getWalker().getCurrentFromClause().isSubQuery() ) {
			resolve( false, true );
		}
		else {
			resolve( true, false );
			Type type = getDataType();
			if ( type.isEntityType() ) {
				FromElement fromElement = getFromElement();
				fromElement.setIncludeSubclasses( true ); // Tell the destination fromElement to 'includeSubclasses'.
				if ( useThetaStyleImplicitJoins ) {
					fromElement.getJoinSequence().setUseThetaStyle( true );    // Use theta style (for regression)
					// Move the node up, after the origin node.
					FromElement origin = fromElement.getOrigin();
					if ( origin != null ) {
						ASTUtil.makeSiblingOfParent( origin, fromElement );
					}
				}
			}
		}

		FromReferenceNode lhs = getLhs();
		while ( lhs != null ) {
			checkSubclassOrSuperclassPropertyReference( lhs, lhs.getNextSibling().getText() );
			lhs = (FromReferenceNode) lhs.getFirstChild();
		}
	}
