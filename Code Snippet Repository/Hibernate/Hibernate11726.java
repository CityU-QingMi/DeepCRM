	public static StandardServiceRegistryBuilder buildBaselineStandardServiceRegistryBuilder(
			  String regionPrefix,
			  Class regionFactory,
			  boolean use2ndLevel,
			  boolean useQueries,
			  Class<? extends JtaPlatform> jtaPlatform) {
		StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder();

		ssrb.applySettings(
				  buildBaselineSettings( regionPrefix, regionFactory, use2ndLevel, useQueries, jtaPlatform )
		);

		return ssrb;
	}
