	@Override
	public Serializable generate(AccessCallback callback) {
		// IMPL NOTE : this method is called concurrently and is
		// not synchronized. It is very important to work on the
		// local variable: the field lastSourceValue is not
		// reliable as it might be mutated by multipled threads.
		// The lastSourceValue field is only accessed by tests,
		// so this is not a concern.
		IntegralDataTypeHolder value = callback.getNextValue();
		lastSourceValue = value;
		return value.makeValue();
	}
