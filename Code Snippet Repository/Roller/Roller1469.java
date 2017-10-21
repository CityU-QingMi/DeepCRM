    public static PingResult sendPing(PingTarget pingTarget, Weblog website) throws IOException, XmlRpcException {
        String websiteUrl = website.getAbsoluteURL();
        String pingTargetUrl = pingTarget.getPingUrl();
        Set variantOptions = PingConfig.getVariantOptions(pingTargetUrl);

        // Set up the ping parameters.
        List params = new ArrayList();
        if (!variantOptions.contains("noname")) {
            // ping variant for icerocket and anyone with similar bug, where we must omit the blog name.
            params.add(website.getName());
        }
        params.add(websiteUrl);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Executing ping to '" + pingTargetUrl + "' for website '" + websiteUrl + "' (" + website.getName() + ")" + (variantOptions.isEmpty() ? "" : " with variant options " + variantOptions));
        }

        // Send the ping.
        XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
        config.setServerURL(new URL(pingTargetUrl));
        XmlRpcClient client = new XmlRpcClient();
        client.setConfig(config);
        PingResult pingResult = parseResult(client.execute("weblogUpdates.ping", params.toArray()));

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Ping result is: " + pingResult);
        }
        return pingResult;
    }
