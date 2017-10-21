    Object referenceKey(K key) {
        switch (keyReferenceType) {
            case STRONG:
                return key;
            case SOFT:
                return new SoftKeyReference(key);
            case WEAK:
                return new WeakKeyReference(key);
            default:
                throw new AssertionError();
        }
    }
