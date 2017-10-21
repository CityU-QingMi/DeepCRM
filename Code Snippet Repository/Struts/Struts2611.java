    private boolean isAssignable(Object value, Class<?> targetClass) {
        if (targetClass == null) {
            return false;
        } else if (value != null && targetClass.isPrimitive()) {
            return false;
        } else if (value != null && !targetClass.isInstance(value)) {
            return false;
        }
        return true;
    }
