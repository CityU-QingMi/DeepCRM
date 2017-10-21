	public void addToOneNotOwningRelation(
			String fromPropertyName,
			String mappedByPropertyName,
			String toEntityName,
			IdMapper idMapper,
			boolean ignoreNotFound) {
		relations.put(
				fromPropertyName,
				RelationDescription.toOne(
						fromPropertyName, RelationType.TO_ONE_NOT_OWNING, toEntityName, mappedByPropertyName,
						idMapper, null, null, true, ignoreNotFound
				)
		);
	}
