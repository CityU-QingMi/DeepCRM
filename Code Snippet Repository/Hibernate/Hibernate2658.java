	public FromElement getFetchOrigin() {
		if ( origin == null ) {
			return null;
		}
		if ( !origin.isFetch() ) {
			return origin;
		}
		if ( origin.getText() == null || "".equals( origin.getText() ) ) {
			return origin.getFetchOrigin();
		}
		return origin;
	}
