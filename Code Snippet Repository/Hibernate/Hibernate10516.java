	@Override
	public boolean equals(Object obj) {
		if ( this == obj ) {
			return true;
		}
		if ( obj == null ) {
			return false;
		}
		if ( getClass() != obj.getClass() ) {
			return false;
		}
		EntityWithEmbeddableWithNoDeclaredData other = (EntityWithEmbeddableWithNoDeclaredData) obj;
		if ( id != other.id ) {
			return false;
		}
		return true;
	}
