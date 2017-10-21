	@Override
	public boolean useFollowOnLocking(QueryParameters parameters) {

		if (parameters != null ) {
			String lowerCaseSQL = parameters.getFilteredSQL().toLowerCase();

			return
				DISTINCT_KEYWORD_PATTERN.matcher( lowerCaseSQL ).find() ||
				GROUP_BY_KEYWORD_PATTERN.matcher( lowerCaseSQL ).find() ||
				UNION_KEYWORD_PATTERN.matcher( lowerCaseSQL ).find() ||
				(
					parameters.hasRowSelection() &&
						(
							ORDER_BY_KEYWORD_PATTERN.matcher( lowerCaseSQL ).find() ||
							parameters.getRowSelection().getFirstRow() != null
						)
				);
		}
		else {
			return true;
		}
	}
