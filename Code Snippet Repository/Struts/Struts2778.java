    private double getVersion(TreeNode webApp) {
        String v = webApp.findAttribute("version");
        if (v != null) {
            try {
                return Double.parseDouble(v);
            } catch (NumberFormatException e) {
            }
        }
        return 2.3;
    }
