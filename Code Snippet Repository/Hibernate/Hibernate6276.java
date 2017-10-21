    @Test
    public void testInconsistentAnnotationPlacement() throws Exception {
        Configuration cfg = new Configuration();
        cfg.addAnnotatedClass( Course1.class );
        cfg.addAnnotatedClass( Student.class );
		SessionFactory sf = null;
		try {
			sf = cfg.buildSessionFactory( serviceRegistry );
			fail( "@Id and @OneToMany are not placed consistently in test entities. SessionFactory creation should fail." );
		}
		catch (MappingException e) {
			// success
		}
		finally {
			if ( sf != null ) {
				sf.close();
			}
		}
	}
