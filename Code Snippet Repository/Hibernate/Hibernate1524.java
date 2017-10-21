	@SuppressWarnings("")
	public static void validateFactory(Object object) {
		try {
			// this direct usage of ClassLoader should be fine since the classes exist in the same jar
			final Class activatorClass = BeanValidationIntegrator.class.getClassLoader().loadClass( ACTIVATOR_CLASS_NAME );
			try {
				final Method validateMethod = activatorClass.getMethod( VALIDATE_SUPPLIED_FACTORY_METHOD_NAME, Object.class );
				validateMethod.setAccessible( true );
				try {
					validateMethod.invoke( null, object );
				}
				catch (InvocationTargetException e) {
					if ( e.getTargetException() instanceof HibernateException ) {
						throw (HibernateException) e.getTargetException();
					}
					throw new HibernateException( "Unable to check validity of passed ValidatorFactory", e );
				}
				catch (IllegalAccessException e) {
					throw new HibernateException( "Unable to check validity of passed ValidatorFactory", e );
				}
			}
			catch (HibernateException e) {
				throw e;
			}
			catch (Exception e) {
				throw new HibernateException( "Could not locate method needed for ValidatorFactory validation", e );
			}
		}
		catch (HibernateException e) {
			throw e;
		}
		catch (Exception e) {
			throw new HibernateException( "Could not locate TypeSafeActivator class", e );
		}
	}
