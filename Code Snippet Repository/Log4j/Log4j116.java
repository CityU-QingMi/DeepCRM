    @Override
    public Object[] swapParameters(final Object[] emptyReplacement) {
        Object[] result;
        if (varargs == null) {
            result = params;
            if (emptyReplacement.length >= MAX_PARMS) {
                params = emptyReplacement;
            } else {
                // Bad replacement! Too small, may blow up future 10-arg messages.
                if (argCount <= emptyReplacement.length) {
                    // copy params into the specified replacement array and return that
                    System.arraycopy(params, 0, emptyReplacement, 0, argCount);
                    result = emptyReplacement;
                } else {
                    // replacement array is too small for current content and future content: discard it
                    params = new Object[MAX_PARMS];
                }
            }
        } else {
            // The returned array will be reused by the caller in future swapParameter() calls.
            // Therefore we want to avoid returning arrays with less than 10 elements.
            // If the vararg array is less than 10 params we just copy its content into the specified array
            // and return it. This helps the caller to retain a reusable array of at least 10 elements.
            // NOTE: LOG4J2-1688 unearthed the use case that an application array (not a varargs array) is passed
            // as the argument array. This array should not be modified, so it cannot be passed to the caller
            // who will at some point null out the elements in the array).
            if (argCount <= emptyReplacement.length) {
                result = emptyReplacement;
            } else {
                result = new Object[argCount]; // LOG4J2-1688
            }
            // copy params into the specified replacement array and return that
            System.arraycopy(varargs, 0, result, 0, argCount);
        }
        return result;
    }
