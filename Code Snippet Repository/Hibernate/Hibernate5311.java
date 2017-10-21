	private SqlTypeDescriptorRegistry() {
		addDescriptor( BooleanTypeDescriptor.INSTANCE );

		addDescriptor( BitTypeDescriptor.INSTANCE );
		addDescriptor( BigIntTypeDescriptor.INSTANCE );
		addDescriptor( DecimalTypeDescriptor.INSTANCE );
		addDescriptor( DoubleTypeDescriptor.INSTANCE );
		addDescriptor( FloatTypeDescriptor.INSTANCE );
		addDescriptor( IntegerTypeDescriptor.INSTANCE );
		addDescriptor( NumericTypeDescriptor.INSTANCE );
		addDescriptor( RealTypeDescriptor.INSTANCE );
		addDescriptor( SmallIntTypeDescriptor.INSTANCE );
		addDescriptor( TinyIntTypeDescriptor.INSTANCE );

		addDescriptor( DateTypeDescriptor.INSTANCE );
		addDescriptor( TimestampTypeDescriptor.INSTANCE );
		addDescriptor( TimeTypeDescriptor.INSTANCE );

		addDescriptor( BinaryTypeDescriptor.INSTANCE );
		addDescriptor( VarbinaryTypeDescriptor.INSTANCE );
		addDescriptor( LongVarbinaryTypeDescriptor.INSTANCE );
		addDescriptor( BlobTypeDescriptor.DEFAULT );

		addDescriptor( CharTypeDescriptor.INSTANCE );
		addDescriptor( VarcharTypeDescriptor.INSTANCE );
		addDescriptor( LongVarcharTypeDescriptor.INSTANCE );
		addDescriptor( ClobTypeDescriptor.DEFAULT );

		addDescriptor( NCharTypeDescriptor.INSTANCE );
		addDescriptor( NVarcharTypeDescriptor.INSTANCE );
		addDescriptor( LongNVarcharTypeDescriptor.INSTANCE );
		addDescriptor( NClobTypeDescriptor.DEFAULT );
	}
