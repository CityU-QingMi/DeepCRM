		@Override
		public Printer<?> getPrinter(SpecialInt annotation, Class<?> fieldType) {
			assertEquals("aliased", annotation.value());
			assertEquals("aliased", annotation.alias());
			return new Printer<Integer>() {
				@Override
				public String print(Integer object, Locale locale) {
					return ":" + object.toString();
				}
			};
		}
