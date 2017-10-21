		@Override
		public void initialize(Serializable id, SharedSessionContractImplementor session)	throws HibernateException {
			Serializable[] batch = session.getPersistenceContext().getBatchFetchQueue()
					.getCollectionBatch( collectionPersister(), id, batchSizes[0] );

			for ( int i=0; i<batchSizes.length-1; i++) {
				final int smallBatchSize = batchSizes[i];
				if ( batch[smallBatchSize-1]!=null ) {
					Serializable[] smallBatch = new Serializable[smallBatchSize];
					System.arraycopy(batch, 0, smallBatch, 0, smallBatchSize);
					loaders[i].loadCollectionBatch( session, smallBatch, collectionPersister().getKeyType() );
					return; //EARLY EXIT!
				}
			}

			loaders[batchSizes.length-1].loadCollection( session, id, collectionPersister().getKeyType() );
		}
