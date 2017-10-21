		@Override
		public void customize(GroovyObject goo) {
			DelegatingMetaClass dmc = new DelegatingMetaClass(goo.getMetaClass()) {
				@Override
				public Object invokeMethod(Object arg0, String mName, Object[] arg2) {
					if (mName.contains("Missing")) {
						throw new IllegalStateException("Gotcha");
					}
					else {
						return super.invokeMethod(arg0, mName, arg2);
					}
				}
			};
			dmc.initialize();
			goo.setMetaClass(dmc);
		}
