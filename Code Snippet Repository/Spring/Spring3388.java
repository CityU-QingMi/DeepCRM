	public static Test suite() {
		GenericApplicationContext ac = new GenericApplicationContext();
		AnnotatedBeanDefinitionReader bdr = new AnnotatedBeanDefinitionReader(ac);
		bdr.setScopeMetadataResolver(new Jsr330ScopeMetadataResolver());

		bdr.registerBean(Convertible.class);
		bdr.registerBean(DriversSeat.class, Drivers.class);
		bdr.registerBean(Seat.class, Primary.class);
		bdr.registerBean(V8Engine.class);
		bdr.registerBean(SpareTire.class, "spare");
		bdr.registerBean(Cupholder.class);
		bdr.registerBean(Tire.class, Primary.class);
		bdr.registerBean(FuelTank.class);

		ac.refresh();
		Car car = ac.getBean(Car.class);

		return Tck.testsFor(car, false, true);
	}
