	@Test
    public void testQueryInAndCompositeWithHQL() {
        Session s = openSession(  );
        Transaction transaction = s.beginTransaction();
        createData( s );
        s.flush();
        List ids = new ArrayList<SomeEntityId>(2);
        ids.add( new SomeEntityId(1,12) );
        ids.add( new SomeEntityId(10,23) );
        ids.add( new SomeEntityId(10,22) );
        Query query=s.createQuery( "from SomeEntity e where e.id in :idList" );
        query.setParameterList( "idList", ids );
        List list=query.list();
        assertEquals( 3, list.size() );
        transaction.rollback();
        s.close();
    }
