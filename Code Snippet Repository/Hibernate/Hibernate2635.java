	private boolean canReuse(String classAlias, FromElement fromElement) {
		// if the from-clauses are the same, we can be a little more aggressive in terms of what we reuse
		if ( fromElement.getFromClause() == getWalker().getCurrentFromClause() &&
				areSame( classAlias, fromElement.getClassAlias() )) {
			return true;
		}

		// otherwise (subquery case) dont reuse the fromElement if we are processing the from-clause of the subquery
		return getWalker().getCurrentClauseType() != SqlTokenTypes.FROM;
	}
