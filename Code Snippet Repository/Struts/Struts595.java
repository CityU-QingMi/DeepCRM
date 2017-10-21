    private java.lang.Number findNumberName() {
        java.lang.Number number = null;
        // find the name on the valueStack
        try {
            // suport Calendar also
            Object numberObject = findValue(name);
            if (numberObject instanceof java.lang.Number) {
                number = (java.lang.Number) numberObject;
            }
        } catch (Exception e) {
            LOG.error("Could not convert object with key [" + name + "] to a java.lang.Number instance");
        }
        return number;
    }
