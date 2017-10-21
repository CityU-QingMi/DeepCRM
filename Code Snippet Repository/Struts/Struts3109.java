    public java.util.List getDependants() {
        try {
            Object target;
            if (isTagFile) {
                if (reload) {
                    tagHandlerClass = ctxt.load();
                    reload = false;
                }
                target = tagHandlerClass.newInstance();
            } else {
                target = getServlet();
            }
            if (target != null && target instanceof JspSourceDependent) {
                return ((java.util.List) ((JspSourceDependent) target).getDependants());
            }
        } catch (Throwable ex) {
        }
        return null;
    }
