	@Override
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( !(o instanceof UnusualIdNamingEntity) ) {
			return false;
		}

		UnusualIdNamingEntity that = (UnusualIdNamingEntity) o;

		if ( uniqueField != null ? !uniqueField.equals( that.uniqueField ) : that.uniqueField != null ) {
			return false;
		}
		if ( variousData != null ? !variousData.equals( that.variousData ) : that.variousData != null ) {
			return false;
		}

		return true;
	}
