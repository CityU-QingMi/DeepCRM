	@SuppressWarnings( {""})
	private EntityPersister createEntityPersister(
			Class<? extends EntityPersister> persisterClass,
			PersistentClass entityBinding,
			EntityRegionAccessStrategy entityCacheAccessStrategy,
			NaturalIdRegionAccessStrategy naturalIdCacheAccessStrategy,
			PersisterCreationContext creationContext) {
		try {
			final Constructor<? extends EntityPersister> constructor = persisterClass.getConstructor( ENTITY_PERSISTER_CONSTRUCTOR_ARGS );
			try {
				return constructor.newInstance(
						entityBinding,
						entityCacheAccessStrategy,
						naturalIdCacheAccessStrategy,
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
					throw new MappingException( "Could not instantiate persister " + persisterClass.getName(), target );
				}
			}
			catch (Exception e) {
				throw new MappingException( "Could not instantiate persister " + persisterClass.getName(), e );
			}
		}
		catch (MappingException e) {
			throw e;
		}
		catch (Exception e) {
			throw new MappingException( "Could not get constructor for " + persisterClass.getName(), e );
		}
	}
