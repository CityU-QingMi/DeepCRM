	@Override
	public boolean equals(Object obj) {
		if ( this == obj ) return true;
		if ( obj == null ) return false;
		if ( getClass() != obj.getClass() ) return false;
		final Carz other = (Carz) obj;
		if ( this.id == null ) {
			if ( other.id != null ) return false;
		}
		else if ( !this.id.equals( other.id ) ) return false;
		if ( this.make == null ) {
			if ( other.make != null ) return false;
		}
		else if ( !this.make.equals( other.make ) ) return false;
		if ( this.manufactured == null ) {
			if ( other.manufactured != null ) return false;
		}
		else if ( !this.manufactured.equals( other.manufactured ) ) return false;
		if ( this.model == null ) {
			if ( other.model != null ) return false;
		}
		else if ( !this.model.equals( other.model ) ) return false;
		return true;
	}
