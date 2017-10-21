	String continueFromManyToMany(String entityName, String[] joinColumns, QueryTranslatorImpl q) throws QueryException {
		start( q );
		continuation = true;
		currentName = q.createNameFor( entityName );
		q.addType( currentName, entityName );
		Queryable classPersister = q.getEntityPersister( entityName );
		//QueryJoinFragment join = q.createJoinFragment(useThetaStyleJoin);
		addJoin( currentName, q.getFactory().getTypeResolver().getTypeFactory().manyToOne( entityName ), joinColumns );
		currentPropertyMapping = classPersister;
		return currentName;
	}
