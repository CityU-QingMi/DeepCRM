    public boolean end(Writer writer, String body) {
        Component component = findAncestor(Component.class);
        if (value != null) {
            if (component instanceof UnnamedParametric) {
                ((UnnamedParametric) component).addParameter(findValue(value));
            } else {
                String name = findString(this.name);

                if (name == null) {
                    throw new StrutsException("No name found for following expression: " + this.name);
                }

                Object value = findValue(this.value);
                if (suppressEmptyParameters) {
                    if (value != null && StringUtils.isNotBlank(value.toString())) {
                        component.addParameter(name, value);
                    } else {
                        component.addParameter(name, null);
                    }
                } else if (value == null || StringUtils.isBlank(value.toString())) {
                    component.addParameter(name, "");
                } else {
                    component.addParameter(name, value);
                }
            }
        } else {
            if (component instanceof UnnamedParametric) {
                ((UnnamedParametric) component).addParameter(body);
            } else {
                if (!(suppressEmptyParameters && StringUtils.isBlank(body))) {
                    component.addParameter(findString(name), body);
                } else {
                    component.addParameter(findString(name), null);
                }
            }
        }

        return super.end(writer, "");
    }
