	@Override
	protected Class[] getAnnotatedClasses() {
		return new Class[]{
				A320b.class, //subclasses should be properly reordered
				Plane.class,
				A320.class,
				Fruit.class,
				//FlyingObject.class, //had to declare embedded superclasses
				//Thing.class,
				Apple.class,
				Music.class,
				Rock.class,
				Funk.class,
				Noise.class
		};
	}
