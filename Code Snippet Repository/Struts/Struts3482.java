    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        PlexusContainer child = null;
        try {
            try {
                HttpServletRequest request = (HttpServletRequest) req;
                HttpSession session = request.getSession(false);
                PlexusContainer parent;
                if (session != null) {
                    parent = (PlexusContainer) session.getAttribute(PlexusLifecycleListener.KEY);
                } else {
                    parent = (PlexusContainer) ctx.getAttribute(PlexusLifecycleListener.KEY);
                }

                if (parent.hasChildContainer(CHILD_CONTAINER_NAME)) {
                    if (LOG.isWarnEnabled()) {
                	LOG.warn("Plexus container (scope: request) alredy exist.");
                    }
                    child = parent.getChildContainer(CHILD_CONTAINER_NAME);
                } else {
                    child = parent.createChildContainer(CHILD_CONTAINER_NAME, Collections.EMPTY_LIST, Collections.EMPTY_MAP);
                    PlexusUtils.configure(child, "plexus-request.xml");
                    child.initialize();
                    child.start();
                }
                PlexusThreadLocal.setPlexusContainer(child);
            } catch (Exception e) {
                LOG.error("Error initializing plexus container (scope: request)", e);
            }

            chain.doFilter(req, res);
        }
        finally {
            try {
                if (child != null) {
                    child.dispose();
                }
                PlexusThreadLocal.setPlexusContainer(null);
            } catch (Exception e) {
                LOG.error("Error disposing plexus container (scope: request)", e);
            }
        }
    }
