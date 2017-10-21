    FunctionInfo createFunctionInfo(TreeNode elem) {

        String name = null;
        String klass = null;
        String signature = null;

        Iterator list = elem.findChildren();
        while (list.hasNext()) {
            TreeNode element = (TreeNode) list.next();
            String tname = element.getName();

            if ("name".equals(tname)) {
                name = element.getBody();
            } else if ("function-class".equals(tname)) {
                klass = element.getBody();
            } else if ("function-signature".equals(tname)) {
                signature = element.getBody();
            } else if ("display-name".equals(tname) || // Ignored elements
                    "small-icon".equals(tname) || "large-icon".equals(tname)
                    || "description".equals(tname) || "example".equals(tname)) {
            } else {
                if (log.isWarnEnabled()) {
                    log.warn(Localizer.getMessage(
                            "jsp.warning.unknown.element.in.function", tname));
                }
            }
        }

        return new FunctionInfo(name, klass, signature);
    }
