	PropertyMapping getPropertyMapping(String name) throws QueryException {
		PropertyMapping decorator = getDecoratedPropertyMapping( name );
		if ( decorator != null ) {
			return decorator;
		}

		String type = getType( name );
		if ( type == null ) {
			String role = getRole( name );
			if ( role == null ) {
				throw new QueryException( "alias not found: " + name );
			}
			return getCollectionPersister( role ); //.getElementPropertyMapping();
		}
		else {
			Queryable persister = getEntityPersister( type );
			if ( persister == null ) {
				throw new QueryException( "persistent class not found: " + type );
			}
			return persister;
		}
	}
