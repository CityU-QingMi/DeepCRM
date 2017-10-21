	@Override
	public boolean equals(Object o) {
		if ( this == o ) return true;
		if ( ! ( o instanceof Contact ) ) return false;

		Contact contact = (Contact) o;

		if ( id != null ? !id.equals( contact.id ) : contact.id != null ) return false;
		if ( firstName != null ? !firstName.equals( contact.firstName ) : contact.firstName != null ) return false;
		if ( lastName != null ? !lastName.equals( contact.lastName ) : contact.lastName != null ) return false;
		if ( type != null ? !type.equals( contact.type ) : contact.type != null ) return false;

		return true;
	}
