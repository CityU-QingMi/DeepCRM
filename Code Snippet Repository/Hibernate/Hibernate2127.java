	@SuppressWarnings("")
	private boolean isEmpty(Object dialectReference) {
		if ( dialectReference != null ) {
			// the referenced value is not null
			if ( dialectReference instanceof String ) {
				// if it is a String, it might still be empty though...
				return StringHelper.isEmpty( (String) dialectReference );
			}
			return false;
		}
		return true;
	}
