    public boolean end(Writer writer, String body) throws StrutsException {
        if (pushed) {
            Object o = getStack().pop();
            if ((o == null) || (!o.equals(textProvider))) {
                LOG.error("A closing i18n tag attempted to pop its own TextProvider from the top of the ValueStack but popped an unexpected object ("+(o != null ? o.getClass() : "null")+"). " +
                            "Refactor the page within the i18n tags to ensure no objects are pushed onto the ValueStack without popping them prior to the closing tag. " +
                            "If you see this message it's likely that the i18n's TextProvider is still on the stack and will continue to provide message resources after the closing tag.");
                throw new StrutsException("A closing i18n tag attempted to pop its TextProvider from the top of the ValueStack but popped an unexpected object ("+(o != null ? o.getClass() : "null")+")");
            }
        }

        return super.end(writer, body);
    }
