	private SingularAttributeImpl locateIdAttribute() {
		if ( id != null ) {
			return id;
		}
		else {
			if ( getSupertype() != null ) {
				SingularAttributeImpl id = getSupertype().internalGetId();
				if ( id != null ) {
					return id;
				}
			}
		}

		return null;
	}
