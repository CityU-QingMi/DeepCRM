	protected SingularAttributeImpl internalGetVersion() {
		if ( version != null ) {
			return version;
		}
		else {
			if ( getSupertype() != null ) {
				return getSupertype().internalGetVersion();
			}
		}

		return null;
	}
