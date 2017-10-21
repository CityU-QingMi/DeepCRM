    protected void populateParams() {
        super.populateParams();

        ListUIBean listUIBean = ((ListUIBean) component);
        listUIBean.setList(list);
        listUIBean.setListKey(listKey);
        listUIBean.setListValue(listValue);
        listUIBean.setListValueKey(listValueKey);
        listUIBean.setListLabelKey(listLabelKey);
        listUIBean.setListCssClass(listCssClass);
        listUIBean.setListCssStyle(listCssStyle);
        listUIBean.setListTitle(listTitle);
    }
