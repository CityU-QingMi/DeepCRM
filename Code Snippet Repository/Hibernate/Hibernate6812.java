	public boolean equals(Object o) {
		if ( this == o ) return true;
		if ( !( o instanceof Node ) ) return false;

		final Node node = (Node) o;

		if ( !id.equals( node.id ) ) return false;

		return true;
	}
