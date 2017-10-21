    TagVariableInfo createVariable(TreeNode elem) {
        String nameGiven = null;
        String nameFromAttribute = null;
        String className = "java.lang.String";
        boolean declare = true;
        int scope = VariableInfo.NESTED;

        Iterator list = elem.findChildren();
        while (list.hasNext()) {
            TreeNode element = (TreeNode) list.next();
            String tname = element.getName();
            if ("name-given".equals(tname))
                nameGiven = element.getBody();
            else if ("name-from-attribute".equals(tname))
                nameFromAttribute = element.getBody();
            else if ("variable-class".equals(tname))
                className = element.getBody();
            else if ("declare".equals(tname)) {
                String s = element.getBody();
                if (s != null)
                    declare = JspUtil.booleanValue(s);
            } else if ("scope".equals(tname)) {
                String s = element.getBody();
                if (s != null) {
                    if ("NESTED".equals(s)) {
                        scope = VariableInfo.NESTED;
                    } else if ("AT_BEGIN".equals(s)) {
                        scope = VariableInfo.AT_BEGIN;
                    } else if ("AT_END".equals(s)) {
                        scope = VariableInfo.AT_END;
                    }
                }
            } else if ("description".equals(tname) || // Ignored elements
            false) {
            } else {
                if (log.isWarnEnabled()) {
                    log.warn(Localizer.getMessage(
                            "jsp.warning.unknown.element.in.variable", tname));
                }
            }
        }
        return new TagVariableInfo(nameGiven, nameFromAttribute, className,
                declare, scope);
    }
