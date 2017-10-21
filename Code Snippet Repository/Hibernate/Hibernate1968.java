	private boolean isAliasDereferenced(String withClauseFragment, String alias) {
		// See if the with clause contains the join alias
		int index = withClauseFragment.indexOf( alias );
		int dotIndex = index + alias.length();
		if ( index != -1
				// Check that the join alias is not a suffix
				&& ( index == 0 || !Character.isLetterOrDigit( withClauseFragment.charAt( index - 1 ) ) )
				// Check that the join alias gets de-referenced i.e. the next char is a dot
				&& dotIndex < withClauseFragment.length() && withClauseFragment.charAt( dotIndex ) == '.' ) {
			return true;
		}

		return false;
	}
