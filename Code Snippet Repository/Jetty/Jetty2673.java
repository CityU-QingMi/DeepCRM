    @Override
    public void setConfiguration(AuthConfiguration configuration)
    {
        super.setConfiguration(configuration);

        String mna = configuration.getInitParameter("maxNonceAge");
        if (mna != null)
            setMaxNonceAge(Long.valueOf(mna));
        String mnc = configuration.getInitParameter("maxNonceCount");
        if (mnc != null)
            setMaxNonceCount(Integer.valueOf(mnc));
    }
