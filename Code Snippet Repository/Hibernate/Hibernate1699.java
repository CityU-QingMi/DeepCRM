	@Override
	protected SqlTypeDescriptor getSqlTypeDescriptorOverride(final int sqlCode) {
		switch ( sqlCode ) {
			// case Types.BOOLEAN:
			// return BitTypeDescriptor.INSTANCE;
			case Types.CLOB:
				return this.clobTypeDescriptor;
			case Types.NCLOB:
				return this.nClobTypeDescriptor;
			case Types.BLOB:
				return this.blobTypeDescriptor;
			case Types.TINYINT:
				// tinyint is unsigned on HANA
				return SmallIntTypeDescriptor.INSTANCE;
			default:
				return super.getSqlTypeDescriptorOverride( sqlCode );
		}
	}
