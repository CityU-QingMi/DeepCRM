	@Override
	public boolean equals(Object o) {
		if ( !( o instanceof Customer ) ) {
			return false;
		}

		Customer customer = (Customer) o;
		return name.equals( customer.name );

	}
