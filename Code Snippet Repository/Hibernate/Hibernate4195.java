	@SuppressWarnings( {""})
	private CollectionPersister createCollectionPersister(
			Class<? extends CollectionPersister> persisterClass,
			Collection collectionBinding,
			CollectionRegionAccessStrategy cacheAccessStrategy,
			PersisterCreationContext creationContext) {
		try {
			Constructor<? extends CollectionPersister> constructor = persisterClass.getConstructor( COLLECTION_PERSISTER_CONSTRUCTOR_ARGS );
			try {
				return constructor.newInstance(
						collectionBinding,
						cacheAccessStrategy,
						creationContext
				);
			}
			catch (MappingException e) {
				throw e;
			}
			catch (InvocationTargetException e) {
				Throwable target = e.getTargetException();
				if ( target instanceof HibernateException ) {
					throw (HibernateException) target;
				}
				else {
					throw new MappingException( "Could not instantiate collection persister " + persisterClass.getName(), target );
				}
			}
			catch (Exception e) {
				throw new MappingException( "Could not instantiate collection persister " + persisterClass.getName(), e );
			}
		}
		catch (MappingException e) {
			throw e;
		}
		catch (Exception e) {
			throw new MappingException( "Could not get constructor for " + persisterClass.getName(), e );
		}
	}
