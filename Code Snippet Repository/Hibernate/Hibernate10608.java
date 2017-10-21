	@Override
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( !(o instanceof Producer) ) {
			return false;
		}

		Producer producer = (Producer) o;

		if ( getId() != null ? !getId().equals( producer.getId() ) : producer.getId() != null ) {
			return false;
		}
		if ( getName() != null ? !getName().equals( producer.getName() ) : producer.getName() != null ) {
			return false;
		}

		return true;
	}
