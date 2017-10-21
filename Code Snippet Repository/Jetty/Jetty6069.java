    @Override
    public void onHandshakeRequest(UpgradeRequest request)
    {
        if (configurator == null)
        {
            return;
        }

        Map<String, List<String>> headers = request.getHeaders();
        configurator.beforeRequest(headers);

        // Handle cookies
        request.setHeaders(headers);
    }
