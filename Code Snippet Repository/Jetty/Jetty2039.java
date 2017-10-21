    @ManagedOperation(value="")
    public String getCacheNegativeTweakedFrom() {
        if (Security.getProperty(SECURITY_NEGATIVE_TTL) != null) {
            if (System.getProperty(SYSTEM_NEGATIVE_TTL) != null) {
                return BOTH;
            }

            return SECURITY;
        }

        if (System.getProperty(SYSTEM_NEGATIVE_TTL) != null) {
            return SYSTEM;
        }

        return DEFAULT;
    }
