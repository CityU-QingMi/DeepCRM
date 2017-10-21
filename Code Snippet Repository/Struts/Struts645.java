    protected void handleErrorInDevMode(HttpServletResponse response, int code, Exception e) {
        LOG.debug("Exception occurred during processing request: {}", e.getMessage(), e);
        try {
            List<Throwable> chain = new ArrayList<>();
            Throwable cur = e;
            chain.add(cur);
            while ((cur = cur.getCause()) != null) {
                chain.add(cur);
            }

            Writer writer = new StringWriter();
            template.process(createReportData(e, chain), writer);

            response.setContentType("text/html");
            response.getWriter().write(writer.toString());
            response.getWriter().close();
        } catch (Exception exp) {
            try {
                LOG.debug("Cannot show problem report!", exp);
                response.sendError(code, "Unable to show problem report:\n" + exp + "\n\n" + LocationUtils.getLocation(exp));
            } catch (IOException ex) {
                // we're already sending an error, not much else we can do if more stuff breaks
            }
        }
    }
