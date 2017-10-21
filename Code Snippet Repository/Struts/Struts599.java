    public boolean end(Writer writer, String body) {
        Select select = (Select) findAncestor(Select.class);
        if (select == null) {
            LOG.error("incorrect use of OptGroup component, this component must be used within a Select component",
                    new IllegalStateException("incorrect use of OptGroup component, this component must be used within a Select component"));
            return false;
        }
        internalUiBean.start(writer);
        internalUiBean.end(writer, body);

        List listUiBeans = (List) select.getParameters().get(INTERNAL_LIST_UI_BEAN_LIST_PARAMETER_KEY);
        if (listUiBeans == null) {
            listUiBeans = new ArrayList();
        }
        listUiBeans.add(internalUiBean);
        select.addParameter(INTERNAL_LIST_UI_BEAN_LIST_PARAMETER_KEY, listUiBeans);

        return false;
    }
