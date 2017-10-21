	public Object newInstance(
			int index,
			Object[] args) throws InvocationTargetException {
		final Constructor[] constructors = this.type.getConstructors();
		try {
			return constructors[index].newInstance( args );
		}
		catch ( ArrayIndexOutOfBoundsException e ) {
			throw new IllegalArgumentException( "Cannot find matching method/constructor" );
		}
		catch ( InstantiationException e ) {
			throw new InvocationTargetException( e );
		}
		catch ( IllegalAccessException e ) {
			throw new InvocationTargetException( e );
		}
	}
