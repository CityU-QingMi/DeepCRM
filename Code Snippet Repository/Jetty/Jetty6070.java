    @Override
    public void onHandshakeResponse(UpgradeResponse response)
    {
        if (configurator == null)
        {
            return;
        }

        JsrHandshakeResponse hr = new JsrHandshakeResponse(response);
        configurator.afterResponse(hr);
    }
