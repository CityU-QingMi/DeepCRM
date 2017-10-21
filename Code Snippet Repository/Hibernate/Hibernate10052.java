	@Override
	protected SortedMap initializeCollection(int size) {
		if ( comparator == null ) {
			return super.initializeCollection( size );
		}
		try {
			return collectionClass.getConstructor( Comparator.class ).newInstance( comparator );
		}
		catch (InstantiationException e) {
			throw new AuditException( e );
		}
		catch (IllegalAccessException e) {
			throw new AuditException( e );
		}
		catch (NoSuchMethodException e) {
			throw new AuditException( e );
		}
		catch (InvocationTargetException e) {
			throw new AuditException( e );
		}
	}
