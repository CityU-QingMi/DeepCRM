	@Test
	public void testLifecycle() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			Person person = new Person( 1L );
			//tag::collections-map-value-type-entity-key-add-example[]
			person.getPhoneRegister().put(
				new Phone( PhoneType.LAND_LINE, "028-234-9876" ), new Date()
			);
			person.getPhoneRegister().put(
				new Phone( PhoneType.MOBILE, "072-122-9876" ), new Date()
			);
			//end::collections-map-value-type-entity-key-add-example[]
			entityManager.persist( person );
		} );
		doInJPA( this::entityManagerFactory, entityManager -> {
			Person person = entityManager.find( Person.class, 1L );
			Map<Phone, Date> phones = person.getPhoneRegister();
			Assert.assertEquals( 2, phones.size() );
		} );
	}
