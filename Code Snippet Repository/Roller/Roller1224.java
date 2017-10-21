    private void updateProxySettings() {
        String proxyHost = WebloggerRuntimeConfig.getProperty("planet.site.proxyhost");
        int proxyPort = WebloggerRuntimeConfig.getIntProperty("planet.site.proxyport");
        if (proxyHost != null && proxyPort > 0) {
            System.setProperty("proxySet", "true");
            System.setProperty("http.proxyHost", proxyHost);
            System.setProperty("http.proxyPort", Integer.toString(proxyPort));
        }
        /** a hack to set 15 sec timeouts for java.net.HttpURLConnection */
        System.setProperty("sun.net.client.defaultConnectTimeout", "15000");
        System.setProperty("sun.net.client.defaultReadTimeout", "15000");
    }
