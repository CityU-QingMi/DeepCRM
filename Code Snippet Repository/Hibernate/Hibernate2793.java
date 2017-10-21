	private String getPropertyPath() {
		if ( currentProperty == null ) {
			return EntityPersister.ENTITY_ID;
		}
		else {
			if ( componentPath.length() > 0 ) {
				return currentProperty + '.' + componentPath.toString();
			}
			else {
				return currentProperty;
			}
		}
	}
