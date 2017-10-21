	private ELResolver createELResolver() {
		this.instantiated = true;
		if (this.resolver == null) {
			CompositeELResolver r = new CompositeELResolver();
			r.add(new ImplicitObjectELResolver());
			for (Iterator itr = this.resolvers.iterator(); itr.hasNext();) {
				r.add((ELResolver) itr.next());
			}
			r.add(new MapELResolver());
			r.add(new ResourceBundleELResolver());
			r.add(new ListELResolver());
			r.add(new ArrayELResolver());	
			r.add(new BeanELResolver());
			r.add(new ScopedAttributeELResolver());
			this.resolver = r;
		}
		return this.resolver;
	}
