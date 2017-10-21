	@Test
	@TestForIssue( jiraKey = "" )
	public void testSecondaryTables() {
		// HHH-4240 - SecondaryTables not recognized when using JOINED inheritance
		Session s = openSession();
		s.getTransaction().begin();
		
		Company company = new Company();
		company.setCustomerName("Mama");
		company.setCustomerCode("123");
		company.setCompanyName("Mama Mia Pizza");
		company.setCompanyAddress("Rome");
		
		s.persist( company );
		s.getTransaction().commit();
		s.clear();
		
		s = openSession();
		s.getTransaction().begin();
		company = (Company) s.get( Company.class, company.getId());
		assertEquals("Mama", company.getCustomerName());
		assertEquals("123", company.getCustomerCode());
		assertEquals("Mama Mia Pizza", company.getCompanyName());
		assertEquals("Rome", company.getCompanyAddress());
				
		s.delete( company );
		s.getTransaction().commit();
		s.close();
	}
