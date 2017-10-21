	protected final void mutateRowValueConstructorSyntaxesIfNecessary(Type lhsType, Type rhsType) {
		// TODO : this really needs to be delayed until after we definitively know all node types
		// where this is currently a problem is parameters for which where we cannot unequivocally
		// resolve an expected type
		SessionFactoryImplementor sessionFactory = getSessionFactoryHelper().getFactory();
		if ( lhsType != null && rhsType != null ) {
			int lhsColumnSpan = getColumnSpan( lhsType, sessionFactory );
			if ( lhsColumnSpan != getColumnSpan( rhsType, sessionFactory ) ) {
				throw new TypeMismatchException(
						"left and right hand sides of a binary logic operator were incompatibile [" +
								lhsType.getName() + " : " + rhsType.getName() + "]"
				);
			}
			if ( lhsColumnSpan > 1 ) {
				// for dialects which are known to not support ANSI-SQL row-value-constructor syntax,
				// we should mutate the tree.
				if ( !sessionFactory.getDialect().supportsRowValueConstructorSyntax() ) {
					mutateRowValueConstructorSyntax( lhsColumnSpan );
				}
			}
		}
	}
