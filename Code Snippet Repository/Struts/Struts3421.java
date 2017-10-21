    public static URL translateBundleURLToJarURL(URL bundleUrl, Bundle bundle) throws MalformedURLException {
        if (bundleUrl != null && "bundle".equalsIgnoreCase(bundleUrl.getProtocol())) {
            StringBuilder sb = new StringBuilder("jar:");
            sb.append(bundle.getLocation());
            sb.append("!");
            sb.append(bundleUrl.getFile());
            return new URL(sb.toString());
        }

        return bundleUrl;
    }
