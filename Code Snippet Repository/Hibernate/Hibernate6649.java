	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( this.getHabitatId() == null ) ? 0
				: this.getHabitatId().hashCode() );
		result = prime * result + ( ( this.getSpeciesId() == null ) ? 0
				: this.getSpeciesId().hashCode() );
		return result;
	}
