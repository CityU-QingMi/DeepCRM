	private Collection<Decorate> getDecorate(EntityManager manager) {
		Collection<Decorate> founds = new ArrayList<Decorate>();
		Query query = manager.createQuery("SELECT o FROM Decorate o");
		List list = query.getResultList();
		for (Object obj : list) {
			if (obj instanceof Decorate) {
				Decorate decorate = (Decorate) obj;
				founds.add( decorate );
				decorate.getPet().getName(); //load
			}
		}
		return founds;
	}
