    @Test
    public void basicExtendedEnhancementTest() {
        // test uses ObjectAttributeMarkerInterceptor to ensure that field access is routed through enhanced methods

        SimpleEntity entity = new SimpleEntity();
        ( (PersistentAttributeInterceptable) entity ).$$_hibernate_setInterceptor( new ObjectAttributeMarkerInterceptor() );

        Object decoy = new Object();
        entity.anUnspecifiedObject = decoy;

        Object gotByReflection = EnhancerTestUtils.getFieldByReflection( entity, "anUnspecifiedObject" );
        assertNotSame( decoy, gotByReflection );
        assertSame( ObjectAttributeMarkerInterceptor.WRITE_MARKER, gotByReflection );

        Object entityObject = entity.anUnspecifiedObject;

        assertNotSame( decoy, entityObject );
        assertSame( ObjectAttributeMarkerInterceptor.READ_MARKER, entityObject );

        // do some more calls on the various types, without the interceptor
        ( (PersistentAttributeInterceptable) entity ).$$_hibernate_setInterceptor( null );

        entity.id = 1234567890L;
        Assert.assertEquals( 1234567890L, (long) entity.getId() );

        entity.name = "Entity Name";
        assertSame( "Entity Name", entity.name );

        entity.active = true;
        assertTrue( entity.getActive() );

        entity.someStrings = Arrays.asList( "A", "B", "C", "D" );
        assertArrayEquals( new String[]{"A", "B", "C", "D"}, entity.someStrings.toArray() );
    }
