    public ReferenceMap(ReferenceType keyReferenceType,
                        ReferenceType valueReferenceType) {
        ensureNotNull(keyReferenceType, valueReferenceType);

        if (keyReferenceType == ReferenceType.PHANTOM || valueReferenceType == ReferenceType.PHANTOM) {
            throw new IllegalArgumentException("Phantom references not supported.");
        }

        this.delegate = new ConcurrentHashMap<>();
        this.keyReferenceType = keyReferenceType;
        this.valueReferenceType = valueReferenceType;
    }
