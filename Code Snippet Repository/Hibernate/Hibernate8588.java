	@Test
	public void testCompositeIDs() throws Exception {
		Session s = openSession();
		s.beginTransaction();
		FumCompositeID fumKey = fumKey("an instance of fo");
		Fo fo = Fo.newFo( fumKey );
		Properties props = new Properties();
		props.setProperty("foo", "bar");
		props.setProperty("bar", "foo");
		fo.setSerial(props);
		fo.setBuf( "abcdefghij1`23%$*^*$*\n\t".getBytes() );
		s.save( fo );
		s.flush();
		props.setProperty("x", "y");
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		fo = (Fo) s.load( Fo.class, fumKey );
		props = (Properties) fo.getSerial();
		assertTrue( props.getProperty("foo").equals("bar") );
		//assertTrue( props.contains("x") );
		assertTrue( props.getProperty("x").equals("y") );
		assertTrue( fo.getBuf()[0]=='a' );
		fo.getBuf()[1]=(byte)126;
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		fo = (Fo) s.load( Fo.class, fumKey );
		assertTrue( fo.getBuf()[1]==126 );
		assertTrue(
				s.createQuery( "from Fo fo where fo.id.string like 'an instance of fo'" ).iterate().next()==fo
		);
		s.delete(fo);
		s.flush();
		try {
			s.save( Fo.newFo() );
			assertTrue(false);
		}
		catch (Exception e) {
			//System.out.println( e.getMessage() );
		}
		s.getTransaction().commit();
		s.close();
	}
