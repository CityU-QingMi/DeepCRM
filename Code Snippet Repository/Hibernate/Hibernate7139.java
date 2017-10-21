    @Test
    public void basicInterceptableTest() {
        SimpleEntity entity = new SimpleEntity();

        assertTyping( PersistentAttributeInterceptable.class, entity );
        PersistentAttributeInterceptable interceptableEntity = (PersistentAttributeInterceptable) entity;

        assertNull( interceptableEntity.$$_hibernate_getInterceptor() );
        interceptableEntity.$$_hibernate_setInterceptor( new ObjectAttributeMarkerInterceptor() );
        assertNotNull( interceptableEntity.$$_hibernate_getInterceptor() );

        assertNull( EnhancerTestUtils.getFieldByReflection( entity, "anUnspecifiedObject" ) );
        entity.setAnObject( new Object() );

        assertSame( ObjectAttributeMarkerInterceptor.WRITE_MARKER, EnhancerTestUtils.getFieldByReflection( entity, "anUnspecifiedObject" ) );
        assertSame( ObjectAttributeMarkerInterceptor.READ_MARKER, entity.getAnObject() );

        entity.setAnObject( null );
        assertSame( ObjectAttributeMarkerInterceptor.WRITE_MARKER, EnhancerTestUtils.getFieldByReflection( entity, "anUnspecifiedObject" ) );
    }
