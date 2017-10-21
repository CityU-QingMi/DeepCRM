    public String getWeblogURL(Weblog weblog,
                                            String locale,
                                            boolean absolute) {

        StringBuilder url = new StringBuilder();
        if (absolute) {
            String weblogAbsoluteURL =
                WebloggerConfig.getProperty("weblog.absoluteurl." + weblog.getHandle());
            if (weblogAbsoluteURL != null) {
                url.append(weblogAbsoluteURL);
            } else {
                url.append(WebloggerRuntimeConfig.getAbsoluteContextURL());
            }
        } else {
            url.append(WebloggerRuntimeConfig.getRelativeContextURL());
        }

        url.append("/").append(weblog.getHandle()).append("/");

        if (locale != null) {
            url.append(locale).append("/");
        }

        return url.toString();
    }
