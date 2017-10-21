    public void setCategoriesString(List<String> categoryNames) {
        StringBuilder sb = new StringBuilder();
        Iterator cats = categoryNames.iterator();
        while (cats.hasNext()) {
            String catName = (String) cats.next();
            sb.append(catName);
            if (cats.hasNext()) {
                sb.append(",");
            }
        }
        categoriesString = sb.toString();
    }
