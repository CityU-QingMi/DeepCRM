	protected boolean addCondition(StringBuilder buffer, String on) {
		if ( StringHelper.isNotEmpty( on ) ) {
			if ( !on.startsWith( " and" ) ) {
				buffer.append( " and " );
			}
			buffer.append( on );
			return true;
		}
		else {
			return false;
		}
	}
