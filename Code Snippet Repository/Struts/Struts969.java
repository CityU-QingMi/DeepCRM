    protected void populateParams() {
        super.populateParams();

        ((ComboBox) component).setList(list);
        ((ComboBox) component).setListKey(listKey);
        ((ComboBox) component).setListValue(listValue);
        ((ComboBox) component).setHeaderKey(headerKey);
        ((ComboBox) component).setHeaderValue(headerValue);
        ((ComboBox) component).setEmptyOption(emptyOption);
    }
