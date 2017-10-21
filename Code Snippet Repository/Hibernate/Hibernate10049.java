	@Override
	@SuppressWarnings("")
	protected T initializeCollection(int size) {
		try {
			return (T) ReflectHelper.getDefaultConstructor( collectionClass ).newInstance();
		}
		catch (InstantiationException e) {
			throw new AuditException( e );
		}
		catch (IllegalAccessException e) {
			throw new AuditException( e );
		}
		catch (InvocationTargetException e) {
			throw new AuditException( e );
		}
	}
