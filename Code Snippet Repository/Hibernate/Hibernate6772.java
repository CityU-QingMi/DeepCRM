	@Test
	@TestForIssue( jiraKey = "" )
	public void testManyToManyEmbeddableBiDirectionalDotNotationInMappedBy() throws Exception {
		// Section 11.1.25
		// The ManyToMany annotation may be used within an embeddable class contained within an entity class to specify a
		// relationship to a collection of entities[101]. If the relationship is bidirectional and the entity containing
		// the embeddable class is the owner of the relationship, the non-owning side must use the mappedBy element of the
		// ManyToMany annotation to specify the relationship field or property of the embeddable class. The dot (".")
		// notation syntax must be used in the mappedBy element to indicate the relationship attribute within the embedded
		// attribute. The value of each identifier used with the dot notation is the name of the respective embedded field
		// or property.
		Session s;
		s = openSession();
		s.getTransaction().begin();
		Employee e = new Employee();
		e.setName( "Sharon" );
		List<PhoneNumber> phoneNumbers = new ArrayList<PhoneNumber>();
		Collection<Employee> employees = new ArrayList<Employee>();
		employees.add( e );
		ContactInfo contactInfo = new ContactInfo();
		PhoneNumber number = new PhoneNumber();
		number.setEmployees( employees );
		phoneNumbers.add( number );
		contactInfo.setPhoneNumbers( phoneNumbers );
		e.setContactInfo( contactInfo );
		s.persist( e );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.getTransaction().begin();
		e = (Employee)s.get( e.getClass(),e.getId() );
		// follow both directions of many to many association 
		assertEquals("same employee", e.getName(), e.getContactInfo().getPhoneNumbers().get(0).getEmployees().iterator().next().getName());
		s.getTransaction().commit();

		s.close();
	}
