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
		EntityWithEmbeddableWithDeclaredData other = (EntityWithEmbeddableWithDeclaredData) obj;
		if ( id != other.id ) {
			return false;
		}
		return true;
	}
