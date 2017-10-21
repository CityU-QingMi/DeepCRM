	public FromElement findFromElementByUserOrSqlAlias(String userAlias, String sqlAlias) {
		FromElement fromElement = null;
		if ( userAlias != null ) {
			fromElement = getFromElement( userAlias );
		}

		if ( fromElement == null ) {
			fromElement = findFromElementBySqlAlias( sqlAlias );
		}

		return fromElement;
	}
