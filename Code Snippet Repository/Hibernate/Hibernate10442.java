	@Override
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( !(o instanceof DarkCharacter) ) {
			return false;
		}

		DarkCharacter character = (DarkCharacter) o;

		if ( id != character.id ) {
			return false;
		}
		if ( kills != character.kills ) {
			return false;
		}

		return true;
	}
