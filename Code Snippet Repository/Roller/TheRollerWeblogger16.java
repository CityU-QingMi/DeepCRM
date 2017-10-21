    public PlanetGroup getGroup(Planet planet, String handle) throws RollerException {
        TypedQuery<PlanetGroup> q = strategy.getNamedQuery("PlanetGroup.getByPlanetAndHandle", PlanetGroup.class);
        q.setParameter(1, planet.getHandle());
        q.setParameter(2, handle);
        try {
            return q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
