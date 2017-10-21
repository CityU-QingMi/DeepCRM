    private Map<String, Boolean> getIncludesExcludesMap() {
        if (this.includesExcludesMap == null) {
            this.includesExcludesMap = new TreeMap<>();

            if (getAllowedCollection() != null) {
                for (String e : getAllowedCollection()) {
                    this.includesExcludesMap.put(e, Boolean.TRUE);
                }
            }
            if (getBlockedCollection() != null) {
                for (String b : getBlockedCollection()) {
                    this.includesExcludesMap.put(b, Boolean.FALSE);
                }
            }
        }

        return this.includesExcludesMap;
    }
