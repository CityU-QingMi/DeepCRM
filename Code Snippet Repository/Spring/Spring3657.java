		@Override
		public Parser<?> getParser(SpecialInt annotation, Class<?> fieldType) {
			assertEquals("aliased", annotation.value());
			assertEquals("aliased", annotation.alias());
			return new Parser<Integer>() {
				@Override
				public Integer parse(String text, Locale locale) throws ParseException {
					return Integer.parseInt(text.substring(1));
				}
			};
		}
