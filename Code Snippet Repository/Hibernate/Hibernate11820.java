	@ProbeBuilder
	public TestProbeBuilder probeConfiguration(TestProbeBuilder probe) {
		System.out.println( "Configuring probe..." );

		// Note : I found locally that this part is not needed.  But I am leaving this here as I might
		// 		someday have a need for tweaking the probe and I want to remember how it is done...

//		// attempt to override PaxExam's default of dynamically importing everything
//		probe.setHeader( Constants.DYNAMICIMPORT_PACKAGE, "" );
//		// and use defined imports instead
//		probe.setHeader(
//				Constants.IMPORT_PACKAGE,
//				"javassist.util.proxy"
//						+ ",javax.persistence"
//						+ ",javax.persistence.spi"
//						+ ",org.h2"
//						+ ",org.osgi.framework"
//						+ ",org.hibernate"
//						+ ",org.hibernate.envers"
////						+ ",org.hibernate.boot.model"
////						+ ",org.hibernate.boot.registry.selector"
////						+ ",org.hibernate.boot.registry.selector.spi"
////						+ ",org.hibernate.cfg"
////						+ ",org.hibernate.engine.spi"
////						+ ",org.hibernate.integrator.spi"
////						+ ",org.hibernate.proxy"
////						+ ",org.hibernate.service"
////						+ ",org.hibernate.service.spi"
////						+ ",org.ops4j.pax.exam.options"
////						+ ",org.ops4j.pax.exam"
//		);
		probe.setHeader( Constants.BUNDLE_ACTIVATOR, "org.hibernate.osgi.test.client.OsgiTestActivator" );
		return probe;
	}
