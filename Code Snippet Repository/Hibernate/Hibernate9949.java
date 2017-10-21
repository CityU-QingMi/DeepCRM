	public void addToOneRelation(
			String fromPropertyName,
			String toEntityName,
			IdMapper idMapper,
			boolean insertable,
			boolean ignoreNotFound) {
		relations.put(
				fromPropertyName,
				RelationDescription.toOne(
						fromPropertyName, RelationType.TO_ONE, toEntityName, null, idMapper, null,
						null, insertable, ignoreNotFound
				)
		);
	}
