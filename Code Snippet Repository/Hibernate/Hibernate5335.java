    @Test
    public void testSerializationRoundTrip() throws Exception {
        final EntityPersister entityPersister = mock(EntityPersister.class);
        final SessionImplementor sessionImplementor = mock(SessionImplementor.class);
        final SessionFactoryImplementor sessionFactoryImplementor = mock(SessionFactoryImplementor.class);
        final Type mockType = mock(Type.class);
        
        when (entityPersister.getRootEntityName()).thenReturn("EntityName");
        
        when(sessionImplementor.getFactory()).thenReturn(sessionFactoryImplementor);
        
        when(entityPersister.getNaturalIdentifierProperties()).thenReturn(new int[] {0, 1, 2});
        when(entityPersister.getPropertyTypes()).thenReturn(new Type[] {
                mockType,
                mockType,
                mockType
        });
        
        when(mockType.getHashCode(anyObject(), eq(sessionFactoryImplementor))).thenAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                return invocation.getArguments()[0].hashCode();
            }
        });
        
        when(mockType.disassemble(anyObject(), eq(sessionImplementor), eq(null))).thenAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                return invocation.getArguments()[0];
            }
        });

        final NaturalIdCacheKey key = (NaturalIdCacheKey) DefaultCacheKeysFactory.staticCreateNaturalIdKey( new Object[] {"a", "b", "c"}, entityPersister, sessionImplementor );

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(key);
        
        final ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()));
        final NaturalIdCacheKey keyClone = (NaturalIdCacheKey) ois.readObject();
        
        assertEquals(key, keyClone);
        assertEquals(key.hashCode(), keyClone.hashCode());
        assertEquals(key.toString(), keyClone.toString());
        assertEquals(key.getEntityName(), keyClone.getEntityName());
        assertArrayEquals(key.getNaturalIdValues(), keyClone.getNaturalIdValues());
        assertEquals(key.getTenantId(), keyClone.getTenantId());
        
    }
