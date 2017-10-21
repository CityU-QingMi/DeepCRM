    public boolean start(Writer writer) {
        boolean result = super.start(writer);

        ValueStack stack = getStack();
        Iterator iter = stack.getRoot().iterator();
        List stackValues = new ArrayList(stack.getRoot().size());
        while (iter.hasNext()) {
            Object o = iter.next();
            Map values;
            try {
                values = reflectionProvider.getBeanMap(o);
            } catch (Exception e) {
                throw new StrutsException("Caught an exception while getting the property values of " + o, e);
            }
            stackValues.add(new DebugMapEntry(o.getClass().getName(), values));
        }

        addParameter("stackValues", stackValues);

        return result;
    }
