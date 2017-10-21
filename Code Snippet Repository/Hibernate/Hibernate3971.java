	private SingularAttributeImpl locateVersionAttribute() {
		if ( version != null ) {
			return version;
		}
		else {
			if ( getSupertype() != null ) {
				SingularAttributeImpl version = getSupertype().internalGetVersion();
				if ( version != null ) {
					return version;
				}
			}
		}

		return null;
	}
