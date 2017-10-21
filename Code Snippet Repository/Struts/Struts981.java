    public boolean internalContainsKey(Object key) {
        boolean contains = super.internalContainsKey(key);

        // first let's check to see if we contain the requested key
        if (contains) {
            return true;
        }

        // if not, let's search for the key in the ognl value stack
        if (stack != null) {
            Object o = stack.findValue(key.toString());

            if (o != null) {
                return true;
            }

            o = stack.getContext().get(key.toString());
            if (o != null) {
                return true;
            }
        }

        // if we still haven't found it, le's search through our chained contexts
        if (chainedContexts != null) {
            for (int index = 0; index < chainedContexts.length; index++) {
                if (chainedContexts[index].containsKey(key)) {
                    return true;
                }
            }
        }

        // nope, i guess it's really not here
        return false;
    }
