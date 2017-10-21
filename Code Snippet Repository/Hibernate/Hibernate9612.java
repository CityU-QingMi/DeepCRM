	@Test
	public void testStandardBasicSqlTypeDescriptor() {
		// no override
		assertSame( IntegerTypeDescriptor.INSTANCE, remapSqlTypeDescriptor( IntegerTypeDescriptor.INSTANCE ) );

		// A few dialects explicitly override BlobTypeDescriptor.DEFAULT
		if ( PostgreSQL81Dialect.class.isInstance( getDialect() ) || PostgreSQLDialect.class.isInstance( getDialect() ) )  {
			assertSame(
					BlobTypeDescriptor.BLOB_BINDING,
					getDialect().remapSqlTypeDescriptor( BlobTypeDescriptor.DEFAULT )
			);
		}
		else if (SybaseDialect.class.isInstance( getDialect() )) {
			assertSame(
					BlobTypeDescriptor.PRIMITIVE_ARRAY_BINDING,
					getDialect().remapSqlTypeDescriptor( BlobTypeDescriptor.DEFAULT )
			);
		}
		else if ( AbstractHANADialect.class.isInstance( getDialect() ) ) {
			assertSame(
					( (AbstractHANADialect) getDialect() ).getBlobTypeDescriptor(),
					getDialect().remapSqlTypeDescriptor( BlobTypeDescriptor.DEFAULT ) );
		}
		else {
			assertSame(
					BlobTypeDescriptor.DEFAULT,
					getDialect().remapSqlTypeDescriptor( BlobTypeDescriptor.DEFAULT )
			);
		}
	}
