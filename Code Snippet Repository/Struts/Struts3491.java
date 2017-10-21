    private Object lookup(String role, String roleHint, Map extraContext)
            throws Exception {
        PlexusContainer pc = PlexusThreadLocal.getPlexusContainer();

        if (pc == null) {
            pc = base;
        }

        try {
            return pc.lookup(role, roleHint);
        }
        catch (Exception e) {
            if (LOG.isDebugEnabled()) {
        	LOG.debug("Can't load component (" + role + "/" + roleHint + ") with plexus, try now with struts.", e);
            }
            Object o = super.buildBean(super.getClassInstance(role), extraContext);
            pc.autowire(o);
            return o;
        }
    }
