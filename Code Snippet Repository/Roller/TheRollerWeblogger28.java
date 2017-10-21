    private String[] createCategoryRestrictionAsArray() {
        if (catArray == null && getCategoryRestriction() != null) {
            StringTokenizer toker = new StringTokenizer(getCategoryRestriction(),",");
            catArray = new String[toker.countTokens()];
            int i = 0;
            
            while (toker.hasMoreTokens()) {
                catArray[i++] = toker.nextToken();
            }
        }
        return catArray;
    }
