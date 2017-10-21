    public void addParameter(ColumnSchema param) {

        HsqlName name = param.getName();
        String paramName =
            name == null
            ? HsqlNameManager.getAutoNoNameColumnString(parameterList.size())
            : name.name;

        parameterList.add(paramName, param);
    }
