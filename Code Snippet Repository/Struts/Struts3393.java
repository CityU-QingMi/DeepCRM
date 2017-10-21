    @Override
    public Resource getResource(String location) {
        if (StringUtils.startsWith(location, "/WEB-INF/")) {
            try {
                URL url = new URL("file:/" + System.getProperty("user.dir") + "/src/main/webapp" + location);
                return new UrlResource(url);
            } catch (Exception e) {
                log.error("Error occurred during get resource for location: {}", location, e);
            }
        }

        return super.getResource(location);
    }
