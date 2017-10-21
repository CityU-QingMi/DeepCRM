    public Object internalGet(String key) {
        // first, let's check to see if have the requested value
        if (super.internalContainsKey(key)) {
            return super.internalGet(key);
        }

        // still no luck?  let's look against the value stack
        if (stack != null) {
            Object object = stack.findValue(key);

            if (object != null) {
                return object;
            }

            object = stack.getContext().get(key);
            if (object != null) {
                return object;
            }

        }

        // finally, if we're chained to other contexts, let's look in them
        if (chainedContexts != null) {
            for (int index = 0; index < chainedContexts.length; index++) {
                if (chainedContexts[index].containsKey(key)) {
                    return chainedContexts[index].internalGet(key);
                }
            }
        }

        // nope, i guess it's really not here
        return null;
    }
