    private void writeOptionGroup(ListUIBean listUIBean, Object value) throws IOException {
        Map params = listUIBean.getParameters();
        Attributes attrs = new Attributes();
        attrs.addIfExists("label", params.get("label"))
                .addIfTrue("disabled", params.get("disabled"));
        start("optgroup", attrs);

        //options
        ValueStack stack = context.getStack();
        Object listObj = params.get("list");
        if (listObj != null) {
            Iterator itt = MakeIterator.convert(listObj);
            String listKey = (String) params.get("listKey");
            String listValue = (String) params.get("listValue");
            while (itt.hasNext()) {
                Object optGroupBean = itt.next();
                stack.push(optGroupBean);

                Object tmpKey = stack.findValue(listKey != null ? listKey : "top");
                String tmpKeyStr = StringUtils.defaultString(tmpKey.toString());
                Object tmpValue = stack.findValue(listValue != null ? listValue : "top");
                String tmpValueStr = StringUtils.defaultString(tmpValue.toString());
                boolean selected = ContainUtil.contains(value, tmpKeyStr);
                writeOption(tmpKeyStr, tmpValueStr, selected);

                stack.pop();
            }
        }

        end("optgroup");
    }
