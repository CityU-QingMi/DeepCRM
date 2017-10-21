	void addCollection() {
		final Type type = propertyValue.getType();
		final Value value = propertyValue.getElement();

		final boolean oneToManyAttachedType = type instanceof BagType || type instanceof SetType || type instanceof MapType || type instanceof ListType;
		final boolean inverseOneToMany = (value instanceof OneToMany) && (propertyValue.isInverse());
		final boolean owningManyToOneWithJoinTableBidirectional = (value instanceof ManyToOne) && (propertyAuditingData.getRelationMappedBy() != null);
		final boolean fakeOneToManyBidirectional = (value instanceof OneToMany) && (propertyAuditingData.getAuditMappedBy() != null);

		if ( oneToManyAttachedType && (inverseOneToMany || fakeOneToManyBidirectional || owningManyToOneWithJoinTableBidirectional) ) {
			// A one-to-many relation mapped using @ManyToOne and @OneToMany(mappedBy="...")
			addOneToManyAttached( fakeOneToManyBidirectional  );
		}
		else {
			// All other kinds of relations require a middle (join) table.
			addWithMiddleTable();
		}
	}
