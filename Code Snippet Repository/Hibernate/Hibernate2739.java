	private void renderNonScalarIdentifiers(
			FromElement fromElement,
			int nonscalarSize,
			int j,
			SelectExpression expr,
			ASTAppender appender) {
		if ( !fromElement.getFromClause().isSubQuery() ) {
			if ( !scalarSelect && !getWalker().isShallowQuery() ) {
//				// todo : ugh this is all fugly code
//				if ( expr instanceof MapKeyNode ) {
//					// don't over-write node text
//				}
//				else if ( expr instanceof MapEntryNode ) {
//					// don't over-write node text
//				}
//				else {
//					String text = fromElement.renderIdentifierSelect( nonscalarSize, j );
//					expr.setText( text );
//				}
				String text = fromElement.renderIdentifierSelect( nonscalarSize, j );
				expr.setText( text );
			}
			else {
				String text = fromElement.renderIdentifierSelect( nonscalarSize, j );
				if (! alreadyRenderedIdentifiers.contains(text)) {
					appender.append( SqlTokenTypes.SQL_TOKEN, text, false );
					alreadyRenderedIdentifiers.add(text);
				}
			}
		}
	}
