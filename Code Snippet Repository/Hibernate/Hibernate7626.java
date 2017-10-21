	@Override
	public boolean equals(Object o) {
		if ( this == o ) return true;
		if ( !(o instanceof Product2) ) return false;

		Product2 product2 = (Product2) o;

		if ( description != null ? !description.equals( product2.description ) : product2.description != null ) return false;
		if ( id != null ? !id.equals( product2.id ) : product2.id != null ) return false;

		return true;
	}
