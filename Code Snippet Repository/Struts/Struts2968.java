    String[] createInitParam(TreeNode elem) {
        String[] initParam = new String[2];

        Iterator list = elem.findChildren();
        while (list.hasNext()) {
            TreeNode element = (TreeNode) list.next();
            String tname = element.getName();
            if ("param-name".equals(tname)) {
                initParam[0] = element.getBody();
            } else if ("param-value".equals(tname)) {
                initParam[1] = element.getBody();
            } else if ("description".equals(tname)) {
                 // Do nothing
            } else {
                if (log.isWarnEnabled()) {
                    log.warn(Localizer.getMessage(
                            "jsp.warning.unknown.element.in.initParam", tname));
                }
            }
        }
        return initParam;
    }
