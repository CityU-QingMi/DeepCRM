    public boolean end(Writer writer, String body) {
        this.processingTagBody = false;
        evaluateParams();
        try {
            addParameter("body", body);
            mergeTemplate(writer, buildTemplateName(template, getDefaultTemplate()));
        } catch (Exception e) {
            LOG.error("error when rendering", e);
        }
        finally {
            popComponentStack();
        }

        return false;
    }
