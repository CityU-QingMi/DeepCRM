	@Test
	public void testProxies() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			try {
				//tag::collections-collection-proxy-usage-example[]
				Person person = entityManager.find( Person.class, 1L );
				//Throws java.lang.ClassCastException: org.hibernate.collection.internal.PersistentBag cannot be cast to java.util.ArrayList
				ArrayList<String> phones = (ArrayList<String>) person.getPhones();
				//end::collections-collection-proxy-usage-example[]
			}
			catch (Exception expected) {
				log.error( "Failure", expected );
			}
		} );
	}
