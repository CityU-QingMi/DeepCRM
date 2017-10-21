	public FakeBidirectionalRelationWorkUnit(
			SessionImplementor sessionImplementor,
			String entityName,
			EnversService enversService,
			Serializable id,
			String referencingPropertyName,
			Object owningEntity,
			RelationDescription rd,
			RevisionType revisionType,
			Object index,
			AuditWorkUnit nestedWorkUnit) {
		super( sessionImplementor, entityName, enversService, id, revisionType );
		this.nestedWorkUnit = nestedWorkUnit;

		// Adding the change for the relation.
		fakeRelationChanges = new HashMap<>();
		fakeRelationChanges.put(
				referencingPropertyName, new FakeRelationChange(
				owningEntity,
				rd,
				revisionType,
				index
		)
		);
	}
