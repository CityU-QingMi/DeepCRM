	@Test
	public void testTypeDefNameAndDefaultForTypeAttributes() {
		ContactDetails contactDetails = new ContactDetails();
		contactDetails.setLocalPhoneNumber(new PhoneNumber("999999"));
		contactDetails.setOverseasPhoneNumber(
				new OverseasPhoneNumber("041", "111111"));
		
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		s.persist(contactDetails);
		tx.commit();
		s.close();
		
		s = openSession();
		tx = s.beginTransaction();
		contactDetails = 
			(ContactDetails) s.get( ContactDetails.class, contactDetails.getId() );
		assertNotNull( contactDetails );
		assertEquals( "999999", contactDetails.getLocalPhoneNumber().getNumber() );
		assertEquals( "041111111", contactDetails.getOverseasPhoneNumber().getNumber() );
		s.delete(contactDetails);
		tx.commit();
		s.close();
	
	}
