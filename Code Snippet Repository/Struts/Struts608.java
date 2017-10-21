    public boolean end(Writer writer, String body) {
        ValueStack stack = getStack();

        Object o;
        if (value == null) {
            if (body != null && !body.equals("")) {
                o = body;
            } else {
                o = findValue("top");
            }
        } else {
            o = findValue(value);
        }

        body="";

        if ("application".equalsIgnoreCase(scope)) {
            stack.setValue("#application['" + getVar() + "']", o);
        } else if ("session".equalsIgnoreCase(scope)) {
            stack.setValue("#session['" + getVar() + "']", o);
        } else if ("request".equalsIgnoreCase(scope)) {
            stack.setValue("#request['" + getVar() + "']", o);
        } else if ("page".equalsIgnoreCase(scope)) {
            stack.setValue("#attr['" + getVar() + "']", o, false);
        } else {
            stack.getContext().put(getVar(), o);
            stack.setValue("#attr['" + getVar() + "']", o, false);
        }

        return super.end(writer, body);
    }
