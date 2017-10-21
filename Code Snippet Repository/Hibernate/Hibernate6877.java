	@Override
	public boolean equals(Object o) {
		if ( this == o ) return true;
		if ( ! ( o instanceof Visitor) ) return false;

		Visitor visitor = (Visitor) o;

		if ( firstName != null ? !firstName.equals( visitor.firstName ) : visitor.firstName != null ) return false;
		if ( id != null ? !id.equals( visitor.id ) : visitor.id != null ) return false;
		if ( lastName != null ? !lastName.equals( visitor.lastName ) : visitor.lastName != null ) return false;

		return true;
	}
