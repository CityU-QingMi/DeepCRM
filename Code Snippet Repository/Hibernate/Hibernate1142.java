	@Override
	public Object newInstance() {
		try {
			return fastClass.newInstance();
		}
		catch ( Exception e ) {
			throw new InstantiationException(
					"Could not instantiate entity with Javassist optimizer: ",
					fastClass.getJavaClass(),
					e
			);
		}
	}
