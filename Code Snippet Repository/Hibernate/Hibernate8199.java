	@Override
	protected void prepareTest() throws Exception {
	    Session s = openSession();
	    Transaction t = s.beginTransaction();
	    Child child_1_1 = new Child( "achild1-1");
	    Child child_1_2 = new Child( "ychild1-2");
	    Child child_1_3 = new Child( "dchild1-3");
	    Child child_2_1 = new Child( "bchild2-1");
	    Child child_2_2 = new Child( "cchild2-2");
	    Child child_2_3 = new Child( "zchild2-3");
	
	    s.save( child_1_1 );
	    s.save( child_2_1 );
	    s.save( child_1_2 );
	    s.save( child_2_2 );
	    s.save( child_1_3 );
	    s.save( child_2_3 );
	
	    s.flush();
	
	    Parent p1 = new Parent( "parent1" );
	    p1.addChild( child_1_1 );
	    p1.addChild( child_1_2 );
	    p1.addChild( child_1_3 );
	    s.save( p1 );
	
	    Parent p2 = new Parent( "parent2" );
	    p2.addChild( child_2_1 );
	    p2.addChild( child_2_2 );
	    p2.addChild( child_2_3 );
	    s.save( p2 );
	
	    t.commit();
	    s.close();
	}
