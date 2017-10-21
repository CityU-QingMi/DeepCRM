    private void setBrowserCachingPolicy(HttpServletResponse res) {
        if (nocache) {
            // HTTP/1.1 + IE extensions
            res.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, " + "post-check=0, pre-check=0");
            // HTTP/1.0
            res.setHeader("Pragma", "no-cache");
            // Last resort for those that ignore all of the above
            res.setHeader("Expires", freemarkerManager.EXPIRATION_DATE);
        }
    }
