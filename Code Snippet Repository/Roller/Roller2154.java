    public String action(String action, String namespace) {
        if(namespace != null) {
            if("/roller-ui".equals(namespace)) {
                return urlStrategy.getActionURL(action, namespace, null, null, true);
            } else if("/roller-ui/authoring".equals(namespace)) {
                return urlStrategy.getActionURL(action, namespace, weblog.getHandle(), null, true);
            } else if("/roller-ui/admin".equals(namespace)) {
                return urlStrategy.getActionURL(action, namespace, null, null, true);
            }
        }
        return null;
    }
