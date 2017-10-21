	@PrePersist
	@PreUpdate
	public void testCountryName(Object object) {
		if ( object instanceof Translation ) {
			Translation tr = (Translation) object;
			if ( ! countries.contains( tr.getInto() ) ) {
				throw new IllegalArgumentException( "Not a country name: " + tr.getInto() );
			}
		}
	}
