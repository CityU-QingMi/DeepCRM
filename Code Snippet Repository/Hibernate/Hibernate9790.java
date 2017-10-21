		@Override
		public void run() {
			LOG.debug( "Running mbean initializer task for ehcache hibernate..." );
			startTime = System.currentTimeMillis();
			if ( mbeanRegistered.compareAndSet( false, true ) ) {
				try {
					ehcacheHibernateMBeanRegistration.registerMBeanForCacheManager( manager, properties );
					LOG.debug( "Successfully registered bean" );
				}
				catch (Exception e) {
					throw new CacheException( e );
				}
			}
			final SessionFactory sessionFactory = locateSessionFactory();
			if ( sessionFactory == null ) {
				LOG.debug(
						"SessionFactory is probably still being initialized..."
								+ " waiting for it to complete before enabling hibernate statistics monitoring via JMX"
				);
				if ( System.currentTimeMillis() > startTime + (NUM_SECONDS * MILLIS_PER_SECOND) ) {
					LOG.info( "Hibernate statistics monitoring through JMX is DISABLED." );
					LOG.info(
							"Failed to look up SessionFactory after " + NUM_SECONDS + " seconds using session-factory properties '"
									+ properties + "'"
					);
					this.cancel();
				}
			}
			else {
				ehcacheHibernateMBeanRegistration.enableHibernateStatisticsSupport( sessionFactory );
				LOG.info( "Hibernate statistics monitoring through JMX is ENABLED. " );
				this.cancel();
			}
		}
