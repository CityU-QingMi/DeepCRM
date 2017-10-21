	private Componentizable setupComponentData() throws SQLException {
		Session session = sessionFactory().openSession();
		session.beginTransaction();

		Componentizable c = new Componentizable();
	    c.setNickName( "Flacky" );
	    Component component = new Component();
	    component.setName("flakky comp");
	    SubComponent subComponent = new SubComponent();
	    subComponent.setSubName("subway");
        component.setSubComponent( subComponent );
	    
        c.setComponent( component );
        
        session.save( c );
		session.getTransaction().commit();
        session.clear();

		return c;
	}
