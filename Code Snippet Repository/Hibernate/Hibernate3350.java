	@Override
	public void stop() {
		try {
			// if we either started the JMX server or we registered some MBeans we at least need to look up
			// MBean server and do *some* work on shutdown.
			if ( startedServer || registeredMBeans != null ) {
				MBeanServer mBeanServer = findServer();
				if ( mBeanServer == null ) {
					LOG.unableToLocateMBeanServer();
					return;
				}

				// release any MBeans we registered
				if ( registeredMBeans != null ) {
					for ( ObjectName objectName : registeredMBeans ) {
						try {
							LOG.tracev( "Unregistering registered MBean [ON={0}]", objectName );
							mBeanServer.unregisterMBean( objectName );
						}
						catch ( Exception e ) {
							LOG.debugf( "Unable to unregsiter registered MBean [ON=%s] : %s", objectName, e.toString() );
						}
					}
				}

				// stop the MBean server if we started it
				if ( startedServer ) {
					LOG.trace( "Attempting to release created MBeanServer" );
					try {
						MBeanServerFactory.releaseMBeanServer( mBeanServer );
					}
					catch ( Exception e ) {
						LOG.unableToReleaseCreatedMBeanServer( e.toString() );
					}
				}
			}
		}
		finally {
			startedServer = false;
			if ( registeredMBeans != null ) {
				registeredMBeans.clear();
				registeredMBeans = null;
			}
		}
	}
