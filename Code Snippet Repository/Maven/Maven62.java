    public void testVersionComparing()
    {
        assertVersionEqual( "1", "1" );
        assertVersionOlder( "1", "2" );
        assertVersionOlder( "1.5", "2" );
        assertVersionOlder( "1", "2.5" );
        assertVersionEqual( "1", "1.0" );
        assertVersionEqual( "1", "1.0.0" );
        assertVersionOlder( "1.0", "1.1" );
        assertVersionOlder( "1.1", "1.2" );
        assertVersionOlder( "1.0.0", "1.1" );
        assertVersionOlder( "1.1", "1.2.0" );

        assertVersionOlder( "1.1.2.alpha1", "1.1.2" );
        assertVersionOlder( "1.1.2.alpha1", "1.1.2.beta1" );
        assertVersionOlder( "1.1.2.beta1", "1.2" );

        assertVersionOlder( "1.0-alpha-1", "1.0" );
        assertVersionOlder( "1.0-alpha-1", "1.0-alpha-2" );
        assertVersionOlder( "1.0-alpha-2", "1.0-alpha-15" );
        assertVersionOlder( "1.0-alpha-1", "1.0-beta-1" );

        assertVersionOlder( "1.0-beta-1", "1.0-SNAPSHOT" );
        assertVersionOlder( "1.0-SNAPSHOT", "1.0" );
        assertVersionOlder( "1.0-alpha-1-SNAPSHOT", "1.0-alpha-1" );

        assertVersionOlder( "1.0", "1.0-1" );
        assertVersionOlder( "1.0-1", "1.0-2" );
        assertVersionEqual( "2.0-0", "2.0" );
        assertVersionOlder( "2.0", "2.0-1" );
        assertVersionOlder( "2.0.0", "2.0-1" );
        assertVersionOlder( "2.0-1", "2.0.1" );

        assertVersionOlder( "2.0.1-klm", "2.0.1-lmn" );
        assertVersionOlder( "2.0.1", "2.0.1-xyz" );
        assertVersionOlder( "2.0.1-xyz-1", "2.0.1-1-xyz" );

        assertVersionOlder( "2.0.1", "2.0.1-123" );
        assertVersionOlder( "2.0.1-xyz", "2.0.1-123" );

        assertVersionOlder( "1.2.3-10000000000", "1.2.3-10000000001" );
        assertVersionOlder( "1.2.3-1", "1.2.3-10000000001" );
        assertVersionOlder( "2.3.0-v200706262000", "2.3.0-v200706262130" ); // org.eclipse:emf:2.3.0-v200706262000
        // org.eclipse.wst.common_core.feature_2.0.0.v200706041905-7C78EK9E_EkMNfNOd2d8qq
        assertVersionOlder( "2.0.0.v200706041905-7C78EK9E_EkMNfNOd2d8qq", "2.0.0.v200706041906-7C78EK9E_EkMNfNOd2d8qq" );
    }
