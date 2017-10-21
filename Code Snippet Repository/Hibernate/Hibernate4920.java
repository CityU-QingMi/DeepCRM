	public final boolean isInstance(Object object) {
		if ( object instanceof Map ) {
			if ( entityName == null ) {
				return true;
			}
			String type = ( String ) ( ( Map ) object ).get( KEY );
			return type == null || isInstanceEntityNames.contains( type );
		}
		else {
			return false;
		}
	}
