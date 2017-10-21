    @Test
    public void jpaBlobStream() throws Exception {
        Session session = openSession();
        LobEntity o = new LobEntity();

        LobHelper lh = session.getLobHelper();
        LobInputStream lis = new LobInputStream();

        session.getTransaction().begin();

        Blob blob = lh.createBlob(lis, LobEntity.BLOB_LENGTH);
        o.setBlob(blob);
        
        // Regardless if NON_CONTEXTUAL_LOB_CREATION is set to true,
        // ContextualLobCreator should use a NonContextualLobCreator to create
        // a blob Proxy.  If that's the case, the InputStream will not be read
        // until it's persisted with the JDBC driver.
        // Although HHH-7698 was about high memory consumption, this is the best
        // way to test that the high memory use is being prevented.
        assertFalse( lis.wasRead() );

        session.persist(o);
        session.getTransaction().commit();
        
        assertTrue( lis.wasRead() );
        
        session.close();

        lis.close();
    }
