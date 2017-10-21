	@Override
	public boolean equals(Object obj) {
		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		if ( getClass() != obj.getClass() )
			return false;
		LocalizedEmbeddable other = (LocalizedEmbeddable) obj;
		if ( description == null ) {
			if ( other.description != null )
				return false;
		}
		else if ( !description.equals( other.description ) )
			return false;
		if ( name == null ) {
			if ( other.name != null )
				return false;
		}
		else if ( !name.equals( other.name ) )
			return false;
		return true;
	}
