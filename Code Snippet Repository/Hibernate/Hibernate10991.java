	@Override
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( !(o instanceof Node) ) {
			return false;
		}

		Node node = (Node) o;

		if ( data != null ? !data.equals( node.data ) : node.data != null ) {
			return false;
		}
		if ( id != null ? !id.equals( node.id ) : node.id != null ) {
			return false;
		}

		return true;
	}
