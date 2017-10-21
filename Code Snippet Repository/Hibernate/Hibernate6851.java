	@Override
	public boolean equals(Object o) {
		if ( this == o ) return true;
		if ( !( o instanceof Box ) ) return false;

		Box box = (Box) o;

		if ( id != box.id ) return false;

		return true;
	}
