	protected SingularAttributeImpl internalGetId() {
		if ( id != null ) {
			return id;
		}
		else {
			if ( getSupertype() != null ) {
				return getSupertype().internalGetId();
			}
		}

		return null;
	}
