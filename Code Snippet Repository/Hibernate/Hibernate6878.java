	@Override
	public boolean equals(Object o) {
		if ( this == o ) return true;
		if ( ! ( o instanceof Zoo ) ) return false;

		Zoo zoo = (Zoo) o;

		if ( id != null ? !id.equals( zoo.id ) : zoo.id != null ) return false;
		if ( name != null ? !name.equals( zoo.name ) : zoo.name != null ) return false;

		return true;
	}
