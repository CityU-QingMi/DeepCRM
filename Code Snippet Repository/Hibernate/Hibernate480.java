	@Test
	public void testHHH6635() throws Exception {
		MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
		Set<ObjectName> set = mBeanServer.queryNames( null, null );
		boolean mbeanfound = false;
		for ( ObjectName obj : set ) {
			if ( obj.getKeyPropertyListString().indexOf( "PooledDataSource" ) > 0 ) {
				mbeanfound = true;

				// see according c3p0 settings in META-INF/persistence.xml

				int actual_minPoolSize = (Integer) mBeanServer.getAttribute( obj, "minPoolSize" );
				assertEquals( 50, actual_minPoolSize );

				int actual_initialPoolSize = (Integer) mBeanServer.getAttribute( obj, "initialPoolSize" );
				assertEquals( 50, actual_initialPoolSize );

				int actual_maxPoolSize = (Integer) mBeanServer.getAttribute( obj, "maxPoolSize" );
				assertEquals( 800, actual_maxPoolSize );

				int actual_maxStatements = (Integer) mBeanServer.getAttribute( obj, "maxStatements" );
				assertEquals( 50, actual_maxStatements );

				int actual_maxIdleTime = (Integer) mBeanServer.getAttribute( obj, "maxIdleTime" );
				assertEquals( 300, actual_maxIdleTime );

				int actual_idleConnectionTestPeriod = (Integer) mBeanServer.getAttribute(
						obj,
						"idleConnectionTestPeriod"
				);
				assertEquals( 3000, actual_idleConnectionTestPeriod );
				break;
			}
		}

		assertTrue( "PooledDataSource BMean not found, please verify version of c3p0", mbeanfound );
	}
