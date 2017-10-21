    Object referenceValue(Object keyReference, Object value) {
        switch (valueReferenceType) {
            case STRONG:
                return value;
            case SOFT:
                return new SoftValueReference(keyReference, value);
            case WEAK:
                return new WeakValueReference(keyReference, value);
            default:
                throw new AssertionError();
        }
    }
