	@Override
	public RegionFactory create(Class<? extends RegionFactory> strategyClass) {
		assert RegionFactory.class.isAssignableFrom( strategyClass );

		// first look for a constructor accepting Properties
		try {
			final Constructor<? extends RegionFactory> ctor = strategyClass.getConstructor( Properties.class );
			return ctor.newInstance( properties );
		}
		catch ( NoSuchMethodException e ) {
			log.debugf( "RegionFactory impl [%s] did not provide constructor accepting Properties", strategyClass.getName() );
		}
		catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
			throw new ServiceException( "Unable to call constructor of RegionFactory impl [" + strategyClass.getName() + "]", e );
		}

		// next try Map
		try {
			final Constructor<? extends RegionFactory> ctor = strategyClass.getConstructor( Map.class );
			return ctor.newInstance( properties );
		}
		catch ( NoSuchMethodException e ) {
			log.debugf( "RegionFactory impl [%s] did not provide constructor accepting Properties", strategyClass.getName() );
		}
		catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
			throw new ServiceException( "Unable to call constructor of RegionFactory impl [" + strategyClass.getName() + "]", e );
		}

		// finally try no-arg
		try {
			return strategyClass.newInstance();
		}
		catch (IllegalAccessException | InstantiationException e) {
			throw new ServiceException( "Unable to call constructor of RegionFactory impl [" + strategyClass.getName() + "]", e );
		}
	}
