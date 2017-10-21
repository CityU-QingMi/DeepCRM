	@Override
	public boolean equals(Object o) {
		if ( this == o ) return true;
		if ( !( o instanceof Person ) ) return false;

		Person person = (Person) o;

		if ( height != null ? !height.equals( person.height ) : person.height != null ) return false;
		if ( id != null ? !id.equals( person.id ) : person.id != null ) return false;
		if ( name != null ? !name.equals( person.name ) : person.name != null ) return false;
		if ( weight != null ? !weight.equals( person.weight ) : person.weight != null ) return false;

		return true;
	}
