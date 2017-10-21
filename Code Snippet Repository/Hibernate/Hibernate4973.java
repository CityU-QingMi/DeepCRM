	@Override
	public AssociationKey getAssociationKey() {
		final AssociationType type = getType();

		if ( type.isAnyType() ) {
			return new AssociationKey(
					JoinHelper.getLHSTableName( type, attributeNumber(), (OuterJoinLoadable) getSource() ),
					JoinHelper.getLHSColumnNames(
							type,
							attributeNumber(),
							0,
							(OuterJoinLoadable) getSource(),
							sessionFactory()
					)
			);
		}

		final Joinable joinable = type.getAssociatedJoinable( sessionFactory() );

		if ( type.getForeignKeyDirection() == ForeignKeyDirection.FROM_PARENT ) {
			final String lhsTableName;
			final String[] lhsColumnNames;

			if ( joinable.isCollection() ) {
				final QueryableCollection collectionPersister = (QueryableCollection) joinable;
				lhsTableName = collectionPersister.getTableName();
				lhsColumnNames = collectionPersister.getElementColumnNames();
			}
			else {
				final OuterJoinLoadable entityPersister = (OuterJoinLoadable) source();
				lhsTableName = getLHSTableName( type, attributeNumber(), entityPersister );
				lhsColumnNames = getLHSColumnNames( type, attributeNumber(), entityPersister, sessionFactory() );
			}
			return new AssociationKey( lhsTableName, lhsColumnNames );
		}
		else {
			return new AssociationKey( joinable.getTableName(), getRHSColumnNames( type, sessionFactory() ) );
		}
	}
