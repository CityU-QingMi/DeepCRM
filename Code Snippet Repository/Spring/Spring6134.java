	public int throwException(int valueIn) throws Exception {
		counter++;
		if (valueIn==1) {
			throw new IllegalArgumentException("IllegalArgumentException for 1");
		}
		if (valueIn==2) {
			throw new RuntimeException("RuntimeException for 2");
		}
		if (valueIn==4) {
			throw new TestException();
		}
		return valueIn;
	}
