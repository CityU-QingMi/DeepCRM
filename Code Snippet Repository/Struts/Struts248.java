    public static <K, V> ReferenceCache<K, V> of(
            ReferenceType keyReferenceType,
            ReferenceType valueReferenceType,
            final Function<? super K, ? extends V> function) {
        ensureNotNull(function);
        return new ReferenceCache<K, V>(keyReferenceType, valueReferenceType) {
            @Override
            protected V create(K key) {
                return function.apply(key);
            }

            private static final long serialVersionUID = 0;
        };
    }
