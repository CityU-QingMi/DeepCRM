	@Override
	protected Class<?>[] getAnnotatedClasses() {
		return new Class<?>[] {
			Person.class,
            Phone.class,
			Call.class,
			CreditCardPayment.class,
			WireTransferPayment.class
		};
	}
