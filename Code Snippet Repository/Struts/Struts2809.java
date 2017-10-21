    public void checkCompile() {

        if (lastCheck < 0) {
            // Checking was disabled
            return;
        }
        long now = System.currentTimeMillis();
        if (now > (lastCheck + (options.getCheckInterval() * 1000L))) {
            lastCheck = now;
        } else {
            return;
        }

        Object[] wrappers = jsps.values().toArray();
        for (Object wrapper : wrappers) {
            JspServletWrapper jsw = (JspServletWrapper) wrapper;
            JspCompilationContext ctxt = jsw.getJspEngineContext();
            // JspServletWrapper also synchronizes on this when
            // it detects it has to do a reload
            synchronized (jsw) {
                try {
                    ctxt.compile();
                } catch (FileNotFoundException ex) {
                    ctxt.incrementRemoved();
                } catch (Throwable t) {
                    jsw.getServletContext().log("Background compile failed",
                            t);
                }
            }
        }

    }
