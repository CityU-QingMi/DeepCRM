	@Override
	public boolean equals(Object o) {
		if ( this == o ) return true;
		if ( ! ( o instanceof Category ) ) return false;

		Category category = (Category) o;

		if ( id != null ? !id.equals( category.id ) : category.id != null ) return false;
		if ( name != null ? !name.equals( category.name ) : category.name != null ) return false;

		return true;
	}
