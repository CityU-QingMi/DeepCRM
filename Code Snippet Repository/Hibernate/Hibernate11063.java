	@Test
	@Priority(10)
	public void initData() {
		// Revision 1
		getSession().getTransaction().begin();
		StrTestEntity ste = new StrTestEntity( "data" );
		getSession().persist( ste );
		getSession().getTransaction().commit();
		id = ste.getId();
		getSession().close();
	}
