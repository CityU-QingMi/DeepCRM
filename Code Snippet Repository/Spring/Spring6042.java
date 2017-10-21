		@Override
		public Object resolve(EvaluationContext context, String beanName) throws AccessException {
			if (beanName.equals("foo")) {
				return "custard";
			}
			else if (beanName.equals("foo.bar")) {
				return "trouble";
			}
			else if (beanName.equals("&foo")) {
				return "foo factory";
			}
			else if (beanName.equals("goo")) {
				throw new AccessException("DONT ASK ME ABOUT GOO");
			}
			return null;
		}
