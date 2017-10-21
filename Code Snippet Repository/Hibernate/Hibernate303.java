	@Test
	public void testLifecycle() {
		final Calendar calendar = new GregorianCalendar();
		doInJPA( this::entityManagerFactory, entityManager -> {
			entityManager.persist( new DateEvent( calendar ) );
		} );
		doInJPA( this::entityManagerFactory, entityManager -> {
			DateEvent dateEvent = entityManager.createQuery( "from DateEvent", DateEvent.class ).getSingleResult();
			//Assert.assertEquals( calendar, dateEvent.getTimestamp() );
		} );
	}
