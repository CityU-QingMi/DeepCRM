    @ManagedOperation(value="")
    public String getCacheTweakedFrom() {
        if (Security.getProperty(SECURITY_TTL) != null) {
            if (System.getProperty(SYSTEM_TTL) != null) {
                return BOTH;
            }

            return SECURITY;
        }

        if (System.getProperty(SYSTEM_TTL) != null) {
            return SYSTEM;
        }

        return DEFAULT;
    }
