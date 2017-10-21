    List getArgListFromValueStack(Map overrides) {

        ValueStack valueStack = valueStackFactory.createValueStack(ActionContext.getContext().getValueStack());

        // add default values to the bottom of the stack. if no action provides
        // a getter for a param, the default value will be used.
        valueStack.getRoot().add(this.defaultValues);

        // push override parameters onto the stack.
        if (overrides != null && !overrides.isEmpty()) {
            valueStack.push(overrides);
        }

        List args = new ArrayList(params.size());
        for (Param param : getParams()) {
            try {
                args.add(valueStack.findValue(param.getName(), param.getType()));
            } catch (Exception e) {
                throw new RuntimeException("Exception while finding '" + param.getName() + "'.", e);
            }
        }

        return args;
    }
