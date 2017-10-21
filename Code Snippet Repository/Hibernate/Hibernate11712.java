	private void bind(String jndiName, Object who, Class classType, Context ctx) throws Exception {
		// Ah ! This service isn't serializable, so we use a helper class
		NonSerializableFactory.bind(jndiName, who);
		Name n = ctx.getNameParser("").parse(jndiName);
		while (n.size() > 1) {
			String ctxName = n.get(0);
			try {
				ctx = (Context) ctx.lookup(ctxName);
			} catch (NameNotFoundException e) {
				System.out.println("Creating subcontext:" + ctxName);
				ctx = ctx.createSubcontext(ctxName);
			}
			n = n.getSuffix(1);
		}

		// The helper class NonSerializableFactory uses address type nns, we go on to
		// use the helper class to bind the service object in JNDI
		StringRefAddr addr = new StringRefAddr("nns", jndiName);
		Reference ref = new Reference(classType.getName(), addr, NonSerializableFactory.class.getName(), null);
		ctx.rebind(n.get(0), ref);
	}
