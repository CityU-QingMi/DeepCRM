	@Override
	@SuppressWarnings({""})
	public <T> Class<T> classForName(String className) {
		try {
			return (Class<T>) Class.forName( className, true, getAggregatedClassLoader() );
		}
		catch (Exception e) {
			throw new ClassLoadingException( "Unable to load class [" + className + "]", e );
		}
		catch (LinkageError e) {
			throw new ClassLoadingException( "Unable to load class [" + className + "]", e );
		}
	}
