	public Set<String> getToPropertyNames(String fromEntityName, String fromPropertyName, String toEntityName) {
		final Set<String> entityAndParentsNames = getEntityAndParentsNames( fromEntityName );
		final Set<String> toPropertyNames = new HashSet<>();
		for ( RelationDescription relationDescription : getRelationDescriptions( toEntityName ) ) {
			final String relToEntityName = relationDescription.getToEntityName();
			final String mappedByPropertyName = relationDescription.getMappedByPropertyName();
			if ( entityAndParentsNames.contains( relToEntityName ) && mappedByPropertyName != null && mappedByPropertyName
					.equals( fromPropertyName ) ) {
				toPropertyNames.add( relationDescription.getFromPropertyName() );
			}
		}
		return toPropertyNames;
	}
