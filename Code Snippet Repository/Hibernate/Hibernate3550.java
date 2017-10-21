		private JoinWalker buildJoinWalker(
				QueryableCollection collectionPersister,
				SessionFactoryImplementor factory,
				LoadQueryInfluencers influencers) {

			if ( collectionPersister.isOneToMany() ) {
				return new OneToManyJoinWalker( collectionPersister, -1, null, factory, influencers ) {
					@Override
					protected StringBuilder whereString(String alias, String[] columnNames, String subselect, int batchSize) {
						if ( subselect != null ) {
							return super.whereString( alias, columnNames, subselect, batchSize );
						}

						return StringHelper.buildBatchFetchRestrictionFragment( alias, columnNames, getFactory().getDialect() );
					}
				};
			}
			else {
				return new BasicCollectionJoinWalker( collectionPersister, -1, null, factory, influencers ) {
					@Override
					protected StringBuilder whereString(String alias, String[] columnNames, String subselect, int batchSize) {
						if ( subselect != null ) {
							return super.whereString( alias, columnNames, subselect, batchSize );
						}

						return StringHelper.buildBatchFetchRestrictionFragment( alias, columnNames, getFactory().getDialect() );
					}
				};
			}
		}
