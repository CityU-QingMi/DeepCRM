	@Override
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( !(o instanceof SampleClass) ) {
			return false;
		}

		SampleClass sampleClass = (SampleClass) o;

		if ( id != null ? !id.equals( sampleClass.id ) : sampleClass.id != null ) {
			return false;
		}
		if ( type != null ? !type.equals( sampleClass.type ) : sampleClass.type != null ) {
			return false;
		}
		if ( sampleValue != null ? !sampleValue.equals( sampleClass.sampleValue ) : sampleClass.sampleValue != null ) {
			return false;
		}

		return true;
	}
