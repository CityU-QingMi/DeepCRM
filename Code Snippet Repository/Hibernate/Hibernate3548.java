		@Override
		public void initialize(Serializable id, SharedSessionContractImplementor session) throws HibernateException {
			// first, figure out how many batchable ids we have...
			final Serializable[] batch = session.getPersistenceContext()
					.getBatchFetchQueue()
					.getCollectionBatch( collectionPersister(), id, maxBatchSize );
			final int numberOfIds = ArrayHelper.countNonNull( batch );
			if ( numberOfIds <= 1 ) {
				singleKeyLoader.loadCollection( session, id, collectionPersister().getKeyType() );
				return;
			}

			final Serializable[] idsToLoad = new Serializable[numberOfIds];
			System.arraycopy( batch, 0, idsToLoad, 0, numberOfIds );

			batchLoader.doBatchedCollectionLoad( session, idsToLoad, collectionPersister().getKeyType() );
		}
