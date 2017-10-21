    public ExtendedThreadInfoFactory() {
        final Method[] methods = ThreadInfo.class.getMethods();
        boolean basic = true;
        for (final Method method : methods) {
            if (method.getName().equals("getLockInfo")) {
                basic = false;
                break;
            }
        }
        if (basic) {
            throw new IllegalStateException();
        }
    }
