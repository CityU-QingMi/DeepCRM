    public boolean qualified(SubscriptionEntry entry) {
        String[] cats = createCategoryRestrictionAsArray();
        if (cats == null || cats.length == 0) {
            return true;
        }
        for (String cat : cats) {
            if (entry.inCategory(cat)) {
                return true;
            }
        }
        return false;
    }
