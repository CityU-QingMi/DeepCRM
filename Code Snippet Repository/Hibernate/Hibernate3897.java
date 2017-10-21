	public void addDeclaredProperty(Property p) {
		//Do not add duplicate properties
		//TODO is it efficient enough?
		String name = p.getName();
		Iterator it = declaredProperties.iterator();
		while (it.hasNext()) {
			if ( name.equals( ((Property)it.next()).getName() ) ) {
				return;
			}
		}
		declaredProperties.add(p);
	}
