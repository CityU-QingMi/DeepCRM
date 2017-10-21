		public boolean equals(Object other) {
			if ( other == this ) {
				return true;
			}
			if ( !( other instanceof HabitatSpeciesLinkId ) ) {
				return false;
			}
			HabitatSpeciesLinkId mi = ( HabitatSpeciesLinkId ) other;
			return ( habitatId == mi.habitatId || ( habitatId != null && habitatId
					.equals( mi.habitatId ) ) )
					&& ( speciesId == mi.speciesId || ( speciesId != null && speciesId
					.equals( mi.speciesId ) ) );
		}
