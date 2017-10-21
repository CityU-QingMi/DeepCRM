    public boolean start(Writer writer) {
        boolean result = super.start(writer);

        ValueStack stack = getStack();

        try {
            String beanName = findString(name, "name", "Bean name is required. Example: com.acme.FooBean or proper Spring bean ID");
            bean = objectFactory.buildBean(beanName, stack.getContext(), false);
        } catch (Exception e) {
            LOG.error("Could not instantiate bean", e);
            return false;
        }

        // push bean on stack
        stack.push(bean);

        // store for reference later
        putInContext(bean);

        return result;
    }
