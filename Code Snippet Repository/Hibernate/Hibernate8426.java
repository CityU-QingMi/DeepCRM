	@Override
	public boolean equals(Object obj) {
		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		if ( getClass() != obj.getClass() )
			return false;
		DestinationEntity other = (DestinationEntity) obj;
		if ( from == null ) {
			if ( other.from != null )
				return false;
		}
		else if ( !from.equals( other.from ) )
			return false;
		if ( fullNameFrom == null ) {
			if ( other.fullNameFrom != null )
				return false;
		}
		else if ( !fullNameFrom.equals( other.fullNameFrom ) )
			return false;
		return true;
	}
