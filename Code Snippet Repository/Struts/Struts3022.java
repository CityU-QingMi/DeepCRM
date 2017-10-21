    private PageContext internalGetPageContext(Servlet servlet, ServletRequest request,
            ServletResponse response, String errorPageURL, boolean needsSession,
            int bufferSize, boolean autoflush) {
        try {
            PageContext pc;
            if (USE_POOL) {
                PageContextPool pool = localPool.get();
                if (pool == null) {
                    pool = new PageContextPool();
                    localPool.set(pool);
                }
                pc = pool.get();
                if (pc == null) {
                    pc = new PageContextImpl();
                }
            } else {
                pc = new PageContextImpl();
            }
            pc.initialize(servlet, request, response, errorPageURL, 
                    needsSession, bufferSize, autoflush);
            return pc;
        } catch (Throwable ex) {
            /* FIXME: need to do something reasonable here!! */
            log.fatal("Exception initializing page context", ex);
            return null;
        }
    }
