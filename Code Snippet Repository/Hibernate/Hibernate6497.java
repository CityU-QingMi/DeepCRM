	@Test
	@FailureExpected( jiraKey = "" )
	public void testEnumTypeInterpretation() {
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().build();

		try {
			final Metadata metadata = new MetadataSources( ssr )
					.addAnnotatedClass( Customer.class )
					.buildMetadata();

			PersistentClass classMetadata = metadata.getEntityBinding( Customer.class.getName() );
			Property investmentsProperty = classMetadata.getProperty( "investments" );
			Collection investmentsValue = (Collection) investmentsProperty.getValue();
			Component investmentMetadata = (Component) investmentsValue.getElement();
			Value descriptionValue = investmentMetadata.getProperty( "description" ).getValue();
			assertEquals( 1, descriptionValue.getColumnSpan() );
			Column selectable = (Column) descriptionValue.getColumnIterator().next();
			assertEquals( 500, selectable.getLength() );
			Component amountMetadata = (Component) investmentMetadata.getProperty( "amount" ).getValue();
			SimpleValue currencyMetadata = (SimpleValue) amountMetadata.getProperty( "currency" ).getValue();
			CustomType currencyType = (CustomType) currencyMetadata.getType();
			int[] currencySqlTypes = currencyType.sqlTypes( metadata );
			assertEquals( 1, currencySqlTypes.length );
			assertJdbcTypeCode( Types.VARCHAR, currencySqlTypes[0] );
		}
		finally {
			StandardServiceRegistryBuilder.destroy( ssr );
		}
	}
