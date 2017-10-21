	@Test
	public void testCompositePk() throws Exception {
		Session s;
		Transaction tx;
		s = openSession();
		tx = s.beginTransaction();
		ComputerPk cid = new ComputerPk();
		cid.setBrand( "IBM" );
		cid.setModel( "ThinkPad" );
		Computer c = new Computer();
		c.setId( cid );
		c.setCpu( "2 GHz" );
		SerialNumberPk sid = new SerialNumberPk();
		sid.setBrand( cid.getBrand() );
		sid.setModel( cid.getModel() );
		SerialNumber sn = new SerialNumber();
		sn.setId( sid );
		sn.setValue( "REZREZ23424" );
		c.setSerial( sn );
		s.persist( c );
		tx.commit();
		s.close();

		s = openSession();
		tx = s.beginTransaction();
		c = ( Computer ) s.get( Computer.class, cid );
		assertNotNull( c );
		assertNotNull( c.getSerial() );
		assertEquals( sn.getValue(), c.getSerial().getValue() );
		tx.commit();
		s.close();
	}
