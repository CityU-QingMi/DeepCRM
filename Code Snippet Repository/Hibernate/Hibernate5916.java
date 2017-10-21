	@Test
	public void testBasic() throws Exception {
		final EntityType<Fridge> entityType = entityManagerFactory().getMetamodel().entity( Fridge.class );
		final SingularAttribute<? super Fridge,Integer> singularAttribute = entityType.getDeclaredSingularAttribute(
				"temperature",
				Integer.class
		);
//		assertEquals( Integer.class, singularAttribute.getBindableJavaType() );
//		assertEquals( Integer.class, singularAttribute.getType().getJavaType() );
		assertEquals( int.class, singularAttribute.getBindableJavaType() );
		assertEquals( int.class, singularAttribute.getType().getJavaType() );
		assertEquals( Bindable.BindableType.SINGULAR_ATTRIBUTE, singularAttribute.getBindableType() );
		assertFalse( singularAttribute.isId() );
		assertFalse( singularAttribute.isOptional() );
		assertFalse( entityType.getDeclaredSingularAttribute( "brand", String.class ).isOptional() );
		assertEquals( Type.PersistenceType.BASIC, singularAttribute.getType().getPersistenceType() );
		final Attribute<? super Fridge, ?> attribute = entityType.getDeclaredAttribute( "temperature" );
		assertNotNull( attribute );
		assertEquals( "temperature", attribute.getName() );
		assertEquals( Fridge.class, attribute.getDeclaringType().getJavaType() );
		assertEquals( Attribute.PersistentAttributeType.BASIC, attribute.getPersistentAttributeType() );
//		assertEquals( Integer.class, attribute.getJavaType() );
		assertEquals( int.class, attribute.getJavaType() );
		assertFalse( attribute.isAssociation() );
		assertFalse( attribute.isCollection() );

		boolean found = false;
		for (Attribute<Fridge, ?> attr : entityType.getDeclaredAttributes() ) {
			if ("temperature".equals( attr.getName() ) ) {
				found = true;
				break;
			}
		}
		assertTrue( found );
	}
