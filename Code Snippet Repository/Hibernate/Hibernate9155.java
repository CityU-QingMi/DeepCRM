	protected Item findByDescription(SessionBuilder sessionBuilder, final String description) {
		Session s = sessionBuilder.openSession();
		try {
         return (Item) s.createCriteria(Item.class)
               .setCacheable(true)
               .setReadOnly(true)
               .add(Restrictions.eq("description", description))
               .uniqueResult();

      } finally {
         s.close();
      }
	}
