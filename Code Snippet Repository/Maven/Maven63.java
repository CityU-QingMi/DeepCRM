    public void testVersionSnapshotComparing()
    {
        assertVersionEqual( "1-SNAPSHOT", "1-SNAPSHOT" );
        assertVersionOlder( "1-SNAPSHOT", "2-SNAPSHOT" );
        assertVersionOlder( "1.5-SNAPSHOT", "2-SNAPSHOT" );
        assertVersionOlder( "1-SNAPSHOT", "2.5-SNAPSHOT" );
        assertVersionEqual( "1-SNAPSHOT", "1.0-SNAPSHOT" );
        assertVersionEqual( "1-SNAPSHOT", "1.0.0-SNAPSHOT" );
        assertVersionOlder( "1.0-SNAPSHOT", "1.1-SNAPSHOT" );
        assertVersionOlder( "1.1-SNAPSHOT", "1.2-SNAPSHOT" );
        assertVersionOlder( "1.0.0-SNAPSHOT", "1.1-SNAPSHOT" );
        assertVersionOlder( "1.1-SNAPSHOT", "1.2.0-SNAPSHOT" );

        // assertVersionOlder( "1.0-alpha-1-SNAPSHOT", "1.0-SNAPSHOT" );
        assertVersionOlder( "1.0-alpha-1-SNAPSHOT", "1.0-alpha-2-SNAPSHOT" );
        assertVersionOlder( "1.0-alpha-1-SNAPSHOT", "1.0-beta-1-SNAPSHOT" );

        assertVersionOlder( "1.0-beta-1-SNAPSHOT", "1.0-SNAPSHOT-SNAPSHOT" );
        assertVersionOlder( "1.0-SNAPSHOT-SNAPSHOT", "1.0-SNAPSHOT" );
        assertVersionOlder( "1.0-alpha-1-SNAPSHOT-SNAPSHOT", "1.0-alpha-1-SNAPSHOT" );

        assertVersionOlder( "1.0-SNAPSHOT", "1.0-1-SNAPSHOT" );
        assertVersionOlder( "1.0-1-SNAPSHOT", "1.0-2-SNAPSHOT" );
        // assertVersionEqual( "2.0-0-SNAPSHOT", "2.0-SNAPSHOT" );
        assertVersionOlder( "2.0-SNAPSHOT", "2.0-1-SNAPSHOT" );
        assertVersionOlder( "2.0.0-SNAPSHOT", "2.0-1-SNAPSHOT" );
        assertVersionOlder( "2.0-1-SNAPSHOT", "2.0.1-SNAPSHOT" );

        assertVersionOlder( "2.0.1-klm-SNAPSHOT", "2.0.1-lmn-SNAPSHOT" );
        // assertVersionOlder( "2.0.1-xyz-SNAPSHOT", "2.0.1-SNAPSHOT" );
        assertVersionOlder( "2.0.1-SNAPSHOT", "2.0.1-123-SNAPSHOT" );
        assertVersionOlder( "2.0.1-xyz-SNAPSHOT", "2.0.1-123-SNAPSHOT" );
    }
