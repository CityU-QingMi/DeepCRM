    public void refresh(Object clazz) throws WebloggerException {
        if (clazz == null) {
            return;
        }
        try {
            EntityManager em = getEntityManager(true);
            em.refresh(clazz);
        } catch (Exception e) {
            // ignored;
        }
    }
