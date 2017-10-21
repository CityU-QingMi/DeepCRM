	public static InfinispanRegionFactory createRegionFactory(Class<? extends InfinispanRegionFactory> clazz, Properties properties) {
		try {
			try {
				Constructor<? extends InfinispanRegionFactory> constructor = clazz.getConstructor(Properties.class);
				return constructor.newInstance(properties);
			}
			catch (NoSuchMethodException e) {
				return clazz.newInstance();
			}
		}
		catch (RuntimeException e) {
			throw e;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
