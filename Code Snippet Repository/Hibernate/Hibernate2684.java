	String renderPropertySelect(int size, int k, boolean allProperties) {
		checkInitialized();
		if ( persister == null ) {
			return "";
		}
		else {
			String fragment = ( (Queryable) persister ).propertySelectFragment(
					getTableAlias(),
					getSuffix( size, k ),
					allProperties
			);
			return trimLeadingCommaAndSpaces( fragment );
		}
	}
