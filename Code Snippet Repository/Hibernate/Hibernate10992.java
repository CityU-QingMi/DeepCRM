	@Override
	public boolean equals(Object obj) {
		if( this == obj ) {
			return true;
		}
		if( !( obj instanceof ManyToManyCompositeKey ) ) {
			return false;
		}

		ManyToManyCompositeKey m = (ManyToManyCompositeKey) obj;
		if ( oneToMany != null ? !oneToMany.equals( m.oneToMany ) : m.oneToMany != null ) {
			return false;
		}
		if ( manyToOne != null ? !manyToOne.equals( m.manyToOne ) : m.manyToOne != null ) {
			return false;
		}
		return true;
	}
