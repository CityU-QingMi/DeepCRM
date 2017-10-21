	@Override
	public boolean equals(Object obj) {
		if ( this == obj ) {
			return true;
		}
		if ( obj == null ) {
			return false;
		}
		if ( !( obj instanceof HabitatSpeciesLink ) ) {
			return false;
		}
		final HabitatSpeciesLink other = ( HabitatSpeciesLink ) obj;
		if ( this.getHabitatId() == null ) {
			if ( other.getHabitatId() != null ) {
				return false;
			}
		}
		else if ( !this.getHabitatId().equals( other.getHabitatId() ) ) {
			return false;
		}
		if ( this.getSpeciesId() == null ) {
			if ( other.getSpeciesId() != null ) {
				return false;
			}
		}
		else if ( !this.getSpeciesId().equals( other.getSpeciesId() ) ) {
			return false;
		}
		return true;
	}
