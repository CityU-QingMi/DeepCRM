	@Test
    public void testSellCar() throws Exception {
        prepareData();
        Session session = openSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery( "from Seller" );
        Seller seller = (Seller) query.uniqueResult();
        assertNotNull( seller );
        assertEquals( 1, seller.getBuyers().size() );
        tx.commit();
        session.close();
    }
