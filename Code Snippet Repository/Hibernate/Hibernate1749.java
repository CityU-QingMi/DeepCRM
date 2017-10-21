	protected SqlTypeDescriptor getSqlTypeDescriptorOverride(int sqlCode) {
		SqlTypeDescriptor descriptor;
		switch ( sqlCode ) {
			case Types.CLOB: {
				descriptor = useInputStreamToInsertBlob() ? ClobTypeDescriptor.STREAM_BINDING : null;
				break;
			}
			default: {
				descriptor = null;
				break;
			}
		}
		return descriptor;
	}
