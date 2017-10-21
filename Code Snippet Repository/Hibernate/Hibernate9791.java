		private SessionFactory locateSessionFactory() {
			final String jndiName = properties.getProperty( Environment.SESSION_FACTORY_NAME );
			if ( jndiName != null ) {
				return SessionFactoryRegistry.INSTANCE.getNamedSessionFactory( jndiName );
			}
			try {
				final Class factoryType = SessionFactoryRegistry.class;
				final Field instancesField = getField( factoryType, "sessionFactoryMap" );
				instancesField.setAccessible( true );
				final Map map = (Map) instancesField.get( SessionFactoryRegistry.INSTANCE );
				if ( map == null ) {
					return null;
				}
				for ( Object o : map.values() ) {
					final SessionFactory sessionFactory = (SessionFactory) o;
					final Class sessionFactoryType = sessionFactory.getClass();
					final Field propertiesField = getField( sessionFactoryType, "properties" );
					if ( propertiesField != null ) {
						propertiesField.setAccessible( true );
						final Properties props = (Properties) propertiesField.get( sessionFactory );
						if ( props != null && props.equals( properties ) ) {
							return sessionFactory;
						}
					}
				}
			}
			catch (RuntimeException re) {
				LOG.error( "Error locating Hibernate Session Factory", re );
			}
			catch (IllegalAccessException iae) {
				LOG.error( "Error locating Hibernate Session Factory", iae );
			}
			return null;
		}
