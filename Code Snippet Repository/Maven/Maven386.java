    public void testExternalURL()
    {
        assertTrue( DefaultMirrorSelector.isExternalRepo( getRepo( "foo", "http://somehost" ) ) );
        assertTrue( DefaultMirrorSelector.isExternalRepo( getRepo( "foo", "http://somehost:9090/somepath" ) ) );
        assertTrue( DefaultMirrorSelector.isExternalRepo( getRepo( "foo", "ftp://somehost" ) ) );
        assertTrue( DefaultMirrorSelector.isExternalRepo( getRepo( "foo", "http://192.168.101.1" ) ) );
        assertTrue( DefaultMirrorSelector.isExternalRepo( getRepo( "foo", "http://" ) ) );
        // these are local
        assertFalse( DefaultMirrorSelector.isExternalRepo( getRepo( "foo", "http://localhost:8080" ) ) );
        assertFalse( DefaultMirrorSelector.isExternalRepo( getRepo( "foo", "http://127.0.0.1:9090" ) ) );
        assertFalse( DefaultMirrorSelector.isExternalRepo( getRepo( "foo", "file://localhost/somepath" ) ) );
        assertFalse( DefaultMirrorSelector.isExternalRepo( getRepo( "foo", "file://localhost/D:/somepath" ) ) );
        assertFalse( DefaultMirrorSelector.isExternalRepo( getRepo( "foo", "http://localhost" ) ) );
        assertFalse( DefaultMirrorSelector.isExternalRepo( getRepo( "foo", "http://127.0.0.1" ) ) );
        assertFalse( DefaultMirrorSelector.isExternalRepo( getRepo( "foo", "file:///somepath" ) ) );
        assertFalse( DefaultMirrorSelector.isExternalRepo( getRepo( "foo", "file://D:/somepath" ) ) );

        // not a proper url so returns false;
        assertFalse( DefaultMirrorSelector.isExternalRepo( getRepo( "foo", "192.168.101.1" ) ) );
        assertFalse( DefaultMirrorSelector.isExternalRepo( getRepo( "foo", "" ) ) );
    }
