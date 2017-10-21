	@Override
	protected Class[] getAnnotatedClasses() {
		return new Class[]{
				Phone.class,
				Voice.class,
				// Adding Cellular here is a test for HHH-9855
				Cellular.class,
				GSM.class
		};
	}
