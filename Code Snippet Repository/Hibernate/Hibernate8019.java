	@Test
	@TestForIssue( jiraKey = "" )
	public void testManyToManyBulkDelete() {
		Session s = openSession();
		Transaction t = s.beginTransaction();

		Farm farm1 = new Farm();
		farm1.setName( "farm1" );
		Crop crop = new Crop();
		crop.setName( "crop1" );
		farm1.setCrops( new ArrayList() );
		farm1.getCrops().add( crop );
		s.save( farm1 );

		Farm farm2 = new Farm();
		farm2.setName( "farm2" );
		farm2.setCrops( new ArrayList() );
		farm2.getCrops().add( crop );
		s.save( farm2 );
		
		s.flush();
		
		try {
			s.createQuery( "delete from Farm f where f.name='farm1'" ).executeUpdate();
			assertEquals( s.createQuery( "from Farm" ).list().size(), 1 );
			s.createQuery( "delete from Farm" ).executeUpdate();
			assertEquals( s.createQuery( "from Farm" ).list().size(), 0 );
		}
		catch (ConstraintViolationException cve) {
			fail("The join table was not cleared prior to the bulk delete.");
		}
		finally {
			t.rollback();
			s.close();
		}
	}
