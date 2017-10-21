	public OrderingSpecification getOrdering() {
		// IMPL NOTE : the ordering-spec would be either the 2nd or 3rd child (of the overall sort-spec), if it existed,
		// 		depending on whether a collation-spec was specified.

		AST possible = getSortKey().getNextSibling();
		if ( possible == null ) {
			// There was no sort-spec parts specified other then the sort-key so there can be no ordering-spec...
			return null;
		}

		if ( OrderByTemplateTokenTypes.COLLATE == possible.getType() ) {
			// the 2nd child was a collation-spec, so we need to check the 3rd child instead.
			possible = possible.getNextSibling();
		}

		return possible != null && OrderByTemplateTokenTypes.ORDER_SPEC == possible.getType()
				?  ( OrderingSpecification ) possible
				:  null;
	}
