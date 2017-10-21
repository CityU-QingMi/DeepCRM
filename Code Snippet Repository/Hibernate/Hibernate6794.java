	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ( ( this.id == null ) ?
				0 :
				this.id.hashCode() );
		result = PRIME * result + ( ( this.make == null ) ?
				0 :
				this.make.hashCode() );
		result = PRIME * result + ( ( this.manufactured == null ) ?
				0 :
				this.manufactured.hashCode() );
		result = PRIME * result + ( ( this.model == null ) ?
				0 :
				this.model.hashCode() );
		return result;
	}
