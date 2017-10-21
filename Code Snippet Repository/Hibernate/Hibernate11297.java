	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( !(o instanceof MappedGrandparentEntity) ) {
			return false;
		}

		MappedGrandparentEntity that = (MappedGrandparentEntity) o;

		if ( id != null ? !id.equals( that.id ) : that.id != null ) {
			return false;
		}
		if ( grandparent != null ? !grandparent.equals( that.grandparent ) : that.grandparent != null ) {
			return false;
		}
		if ( notAudited != null ? !notAudited.equals( that.notAudited ) : that.notAudited != null ) {
			return false;
		}

		return true;
	}
