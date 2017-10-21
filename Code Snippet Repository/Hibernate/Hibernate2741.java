	public SelectExpression[] collectSelectExpressions() {
		// Get the first child to be considered.  Sub-classes may do this differently in order to skip nodes that
		// are not select expressions (e.g. DISTINCT).
		AST firstChild = getFirstSelectExpression();
		ArrayList<SelectExpression> list = new ArrayList<SelectExpression>();
		int p = 0;
		for ( AST n = firstChild; n != null; n = n.getNextSibling() ) {
			if ( n instanceof SelectExpression ) {
				list.add( (SelectExpression) n );
			}
			else if ( n instanceof ParameterNode ) {
				parameterPositions.add( p );
			}
			else {
				throw new IllegalStateException(
						"Unexpected AST: " + n.getClass().getName() + " "
								+ new ASTPrinter( SqlTokenTypes.class ).showAsString( n, "" )
				);
			}
			p++;
		}
		return list.toArray( new SelectExpression[list.size()] );
	}
