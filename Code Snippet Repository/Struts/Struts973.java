    protected void populateParams() {
        super.populateParams();

        OptGroup optGroup = (OptGroup) component;
        optGroup.setList(list);
        optGroup.setLabel(label);
        optGroup.setDisabled(disabled);
        optGroup.setListKey(listKey);
        optGroup.setListValue(listValue);
    }
