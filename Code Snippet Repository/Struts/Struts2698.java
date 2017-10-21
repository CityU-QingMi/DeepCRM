    public void execute() throws JasperException {
        if (log.isDebugEnabled()) {
            log.debug("execute() starting for " + pages.size() + " pages.");
        }

        try {
            if (context == null) {
                initServletContext();
            }

            initWebXml();

            Iterator iter = pages.iterator();
            while (iter.hasNext()) {
                String nextjsp = iter.next().toString();

                processFile(nextjsp);
            }

            completeWebXml();
        } catch (JasperException je) {
            Throwable rootCause = je;
            while (rootCause instanceof JasperException
                    && ((JasperException) rootCause).getRootCause() != null) {
                rootCause = ((JasperException) rootCause).getRootCause();
            }
            if (rootCause != je) {
                rootCause.printStackTrace();
            }
            throw je;
        }
    }
