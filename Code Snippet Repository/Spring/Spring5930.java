		@Override
		public void write(EvaluationContext context, Object target, String name, Object newValue) throws AccessException {
			if (!name.equals("flibbles")) {
				throw new RuntimeException("Assertion Failed! name should be flibbles");
			}
			try {
				flibbles = (Integer) context.getTypeConverter().convertValue(newValue, TypeDescriptor.forObject(newValue), TypeDescriptor.valueOf(Integer.class));
			}
			catch (EvaluationException ex) {
				throw new AccessException("Cannot set flibbles to an object of type '" + newValue.getClass() + "'");
			}
		}
