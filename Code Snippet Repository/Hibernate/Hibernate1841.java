	@Override
	protected SqlTypeDescriptor getSqlTypeDescriptorOverride(int sqlCode) {
		switch (sqlCode) {
		case Types.BLOB:
			return BlobTypeDescriptor.PRIMITIVE_ARRAY_BINDING;
		case Types.CLOB:
			// Some Sybase drivers cannot support getClob.  See HHH-7889
			return ClobTypeDescriptor.STREAM_BINDING_EXTRACTING;
		default:
			return super.getSqlTypeDescriptorOverride( sqlCode );
		}
	}
