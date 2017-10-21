	public Object loadElement(SharedSessionContractImplementor session, Object key, Object index)
			throws HibernateException {

		List list = loadEntity(
				session,
				key,
				index,
				keyType,
				indexType,
				persister
		);

		if ( list.size() == 1 ) {
			return list.get( 0 );
		}
		else if ( list.size() == 0 ) {
			return null;
		}
		else {
			if ( getCollectionOwners() != null ) {
				return list.get( 0 );
			}
			else {
				throw new HibernateException( "More than one row was found" );
			}
		}

	}
