	@Test
	@TestForIssue( jiraKey = "" )
	public void testBackquotes() {
		try {
			Configuration config = new Configuration();
			config.addAnnotatedClass(Bug.class);
			config.addAnnotatedClass(Category.class);
			sessionFactory = config.buildSessionFactory( serviceRegistry );
		}
		catch( Exception e ) {
			StringWriter writer = new StringWriter();
			e.printStackTrace(new PrintWriter(writer));
            log.debug(writer.toString());
			fail(e.getMessage());
		}
		finally {
			if ( sessionFactory != null ) {
				sessionFactory.close();
				sessionFactory = null;
			}
		}
	}
