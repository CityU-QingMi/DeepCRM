    public boolean isAllowedAction(Bundle bundle, String val) {
        int state = -1;
        try {
            state = bundle.getState();
        } catch (Exception e) {
            addActionError("Unable to determine bundle state: " + e.getMessage());
            return false;
        }

        if ("start".equals(val)) {
            return state == Bundle.RESOLVED;
        } else if ("stop".equals(val)) {
            return state == Bundle.ACTIVE;
        } else if ("update".equals(val)) {
            return state == Bundle.ACTIVE || state == Bundle.INSTALLED
                    || state == Bundle.RESOLVED;
        }
        throw new IllegalArgumentException("Invalid state");
    }
