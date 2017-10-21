	@Test
	public void testLifecycle() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			Person person = new Person( 1L );
			LocalDateTime now = LocalDateTime.now();
			person.addPhone(
				new Phone( PhoneType.LAND_LINE, "028-234-9876", Timestamp.valueOf( now ) )
			);
			person.addPhone(
				new Phone( PhoneType.MOBILE, "072-122-9876", Timestamp.valueOf( now.minusDays( 1 ) ) )
			);
			entityManager.persist( person );
		} );
		doInJPA( this::entityManagerFactory, entityManager -> {
			Person person = entityManager.find( Person.class, 1L );
			Map<Date, Phone> phones = person.getPhoneRegister();
			Assert.assertEquals( 2, phones.size() );
		} );
	}
