	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ( ( this.location == null ) ?
				0 :
				this.location.hashCode() );
		result = PRIME * result + ( ( this.lotPK == null ) ?
				0 :
				this.lotPK.hashCode() );
		result = PRIME * result + ( ( this.name == null ) ?
				0 :
				this.name.hashCode() );
		return result;
	}
