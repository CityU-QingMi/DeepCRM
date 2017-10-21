	protected void assertTranslation(String hql, Map replacements) {
		ComparisonFailure cf = null;
		try {
			assertTranslation( hql, replacements, false, null );
		}
		catch ( ComparisonFailure e ) {
			e.printStackTrace();
			cf = e;
		}
		if ("false".equals(System.getProperty("org.hibernate.test.hql.SkipScalarQuery","false"))) {
			// Run the scalar translation anyway, even if there was a comparison failure.
			assertTranslation( hql, replacements, true, null );
		}
		if (cf != null)
			throw cf;
	}
