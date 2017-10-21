	private SessionFactory getSessionFactory() {
		if ( sf == null ) {
			Bundle thisBundle = FrameworkUtil.getBundle( HibernateUtil.class );
			// Could get this by wiring up OsgiTestBundleActivator as well.
			BundleContext context = thisBundle.getBundleContext();

			ServiceReference sr = context.getServiceReference( SessionFactory.class.getName() );
			sf = (SessionFactory) context.getService( sr );
		}
		return sf;
	}
