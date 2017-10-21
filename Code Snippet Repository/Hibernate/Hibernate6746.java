	@Override
	public void buildEntityManagerFactory() throws Exception {
		try {
			super.buildEntityManagerFactory();
		}
		catch (Exception expected) {
			HibernateException rootCause = (HibernateException) ExceptionUtil.rootCause( expected );
			assertTrue(rootCause.getMessage().contains( "could not resolve property: valid" ));
			assertTrue(rootCause.getMessage().contains( "_Person is not mapped" ));
		}
	}
