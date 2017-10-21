	@Test
	@TestForIssue( jiraKey = "" )
	public void testUniqueConstraintAnnotationOnNaturalIds() throws Exception {
		Configuration configuration = new Configuration();
		configuration.setProperty( Environment.HBM2DDL_AUTO, "create-drop" );
		configuration.addAnnotatedClass(Month.class);
		SessionFactory sf = null;
		try {
			sf = configuration.buildSessionFactory();
			sf.close();
		}
		catch (ConcurrentModificationException e) {
			fail(e.toString()); 
		}
	}
