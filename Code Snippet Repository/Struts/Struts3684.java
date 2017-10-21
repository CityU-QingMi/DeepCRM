    public static View getView(String namespace, String actionName, String resultName, int type) {
        String viewId = namespace + "/" + actionName + "/" + resultName;
        View view = viewCache.get(viewId);
        if (view == null) {
            File viewFile = StrutsConfigRetriever.getViewFile(namespace, actionName, resultName);
            if (viewFile != null) {
                switch (type) {
                    case View.TYPE_JSP:
                        view = new JspView(viewFile);
                        break;
                    case View.TYPE_FTL:
                        view = new FreeMarkerView(viewFile);
                        break;
                    case View.TYPE_VM:
                        view = new VelocityView(viewFile);
                        break;
                    default:
                        return null;
                }

                viewCache.put(viewId, view);
            }
        }
        return view;
    }
