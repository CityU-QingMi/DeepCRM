	@Test
	public void testDottedNotation() throws Exception {
		assertTrue( SchemaUtil.isTablePresent( "Employee", metadata() ) );
		assertTrue( "Overridden @JoinColumn fails",
				SchemaUtil.isColumnPresent( "Employee", "fld_address_fk", metadata() ) );

		assertTrue( "Overridden @JoinTable name fails", SchemaUtil.isTablePresent( "tbl_empl_sites", metadata() ) );
		assertTrue( "Overridden @JoinTable with default @JoinColumn fails",
				SchemaUtil.isColumnPresent( "tbl_empl_sites", "employee_id", metadata() ) );
		assertTrue( "Overridden @JoinTable.inverseJoinColumn fails",
				SchemaUtil.isColumnPresent( "tbl_empl_sites", "to_website_fk", metadata() ) );

		Session s = openSession();
		Transaction tx = s.beginTransaction();
		ContactInfo ci = new ContactInfo();
		Addr address = new Addr();
		address.setCity("Boston");
		address.setCountry("USA");
		address.setState("MA");
		address.setStreet("27 School Street");
		address.setZipcode("02108");
		ci.setAddr(address);
		List<PhoneNumber> phoneNumbers = new ArrayList();
		PhoneNumber num = new PhoneNumber();
		num.setNumber(5577188);
		Employee e = new Employee();
		Collection employeeList = new ArrayList();
		employeeList.add(e);
		e.setContactInfo(ci);
		num.setEmployees(employeeList);
		phoneNumbers.add(num);
		ci.setPhoneNumbers(phoneNumbers);
		SocialTouchPoints socialPoints = new SocialTouchPoints();
		List<SocialSite> sites = new ArrayList<SocialSite>();
		SocialSite site = new SocialSite();
		site.setEmployee(employeeList);
		site.setWebsite("www.jboss.org");
		sites.add(site);
		socialPoints.setWebsite(sites);
		ci.setSocial(socialPoints);
		s.persist(e);
		tx.commit();

		tx = s.beginTransaction();
		s.clear();
		e = (Employee) s.get(Employee.class,e.getId());
		tx.commit();
		s.close();
	}
