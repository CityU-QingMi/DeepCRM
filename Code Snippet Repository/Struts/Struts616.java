    public boolean end(Writer writer, String body) {
        evaluateParams();
        try {
            super.end(writer, body, false);
            mergeTemplate(writer, buildTemplateName(template, getDefaultTemplate()));
        } catch (Exception e) {
            throw new StrutsException(e);
        }
        finally {
            popComponentStack();
        }

        return false;
    }
