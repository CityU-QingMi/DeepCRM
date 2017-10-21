	public static void sortFactoryMethods(Method[] factoryMethods) {
		Arrays.sort(factoryMethods, new Comparator<Method>() {
			@Override
			public int compare(Method fm1, Method fm2) {
				boolean p1 = Modifier.isPublic(fm1.getModifiers());
				boolean p2 = Modifier.isPublic(fm2.getModifiers());
				if (p1 != p2) {
					return (p1 ? -1 : 1);
				}
				int c1pl = fm1.getParameterCount();
				int c2pl = fm2.getParameterCount();
				return (c1pl < c2pl ? 1 : (c1pl > c2pl ? -1 : 0));
			}
		});
	}
