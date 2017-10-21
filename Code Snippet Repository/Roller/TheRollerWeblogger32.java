    public List<Category> getCategories() {
        List<Category> list = new ArrayList<Category>();
        if (getCategoriesString() != null) {
            String[] catArray = Utilities.stringToStringArray(getCategoriesString(),",");
            for (String catName : catArray) {
                Category cat = new Category();
                cat.setName(catName);
                list.add(cat);
            }
        }
        return list;
    }
