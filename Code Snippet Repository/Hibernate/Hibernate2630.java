	@Override
	public String getPath() {
		if ( path == null ) {
			FromReferenceNode lhs = getLhs();
			if ( lhs == null ) {
				path = getText();
			}
			else {
				SqlNode rhs = (SqlNode) lhs.getNextSibling();
				path = lhs.getPath() + "." + rhs.getOriginalText();
			}
		}
		return path;
	}
