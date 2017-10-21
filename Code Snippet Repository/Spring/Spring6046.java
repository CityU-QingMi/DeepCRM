		@Override
		public Class<?> findType(String typeName) throws EvaluationException {
			if (typeName.equals("Spr5899Class")) {
				return Spr5899Class.class;
			}
			if (typeName.equals("Outer")) {
				return Outer.class;
			}
			return super.findType(typeName);
		}
