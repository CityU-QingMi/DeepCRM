	public void addToManyNotOwningRelation(
			String fromPropertyName,
			String mappedByPropertyName,
			String toEntityName,
			IdMapper idMapper,
			PropertyMapper fakeBidirectionalRelationMapper,
			PropertyMapper fakeBidirectionalRelationIndexMapper,
			boolean indexed) {
		relations.put(
				fromPropertyName,
				RelationDescription.toMany(
						fromPropertyName, RelationType.TO_MANY_NOT_OWNING, toEntityName, mappedByPropertyName,
						idMapper, fakeBidirectionalRelationMapper, fakeBidirectionalRelationIndexMapper, true, indexed
				)
		);
	}
