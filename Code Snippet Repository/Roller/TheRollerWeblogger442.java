    public List<WeblogCategoryWrapper> getWeblogCategories() {
        List<WeblogCategory> unwrapped = this.pojo.getWeblogCategories();
        List<WeblogCategoryWrapper> wrapped = new ArrayList<WeblogCategoryWrapper>(unwrapped.size());

        int i = 0;
        for (WeblogCategory category : unwrapped) {
            wrapped.add(i,WeblogCategoryWrapper.wrap(category, urlStrategy));
            i++;
        }
        return wrapped;
    }
