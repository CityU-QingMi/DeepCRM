	@Override
	public AST processEqualityExpression(AST x) {
		if ( x == null ) {
			LOG.processEqualityExpression();
			return null;
		}

		int type = x.getType();
		if ( type == EQ || type == NE ) {
			boolean negated = type == NE;
			if ( x.getNumberOfChildren() == 2 ) {
				AST a = x.getFirstChild();
				AST b = a.getNextSibling();
				// (EQ NULL b) => (IS_NULL b)
				if ( a.getType() == NULL && b.getType() != NULL ) {
					return createIsNullParent( b, negated );
				}
				// (EQ a NULL) => (IS_NULL a)
				else if ( b.getType() == NULL && a.getType() != NULL ) {
					return createIsNullParent( a, negated );
				}
				else if ( b.getType() == EMPTY ) {
					return processIsEmpty( a, negated );
				}
				else {
					return x;
				}
			}
			else {
				return x;
			}
		}
		else {
			return x;
		}
	}
