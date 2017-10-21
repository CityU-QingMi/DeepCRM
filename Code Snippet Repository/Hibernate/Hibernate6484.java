	@Test
	@TestForIssue( jiraKey = "" )
	public void testIt() {
		final PersistentClass entityBinding = metadata().getEntityBinding( AggregatedTypeValue.class.getName() );
		final Property attributesBinding = entityBinding.getProperty( "attributes" );
		final org.hibernate.mapping.Map attributesMap = (org.hibernate.mapping.Map) attributesBinding.getValue();

		final SimpleValue mapKey = assertTyping( SimpleValue.class, attributesMap.getIndex() );
		final BasicType mapKeyType = assertTyping( BasicType.class, mapKey.getType() );
		assertTrue( String.class.equals( mapKeyType.getReturnedClass() ) );

		// let's also make sure the @MapKeyColumn got applied
		assertThat( mapKey.getColumnSpan(), is(1) );
		final org.hibernate.mapping.Column mapKeyColumn = assertTyping( org.hibernate.mapping.Column .class, mapKey.getColumnIterator().next() );
		assertThat( mapKeyColumn.getName(), equalTo( "attribute_name" ) );
	}
