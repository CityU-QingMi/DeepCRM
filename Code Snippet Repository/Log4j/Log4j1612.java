    public void store(final SmtpResponse response, final String params) {
        if (params != null) {
            if (SmtpState.DATA_HDR.equals(response.getNextState())) {
                final int headerNameEnd = params.indexOf(':');
                if (headerNameEnd >= 0) {
                    final String name = params.substring(0, headerNameEnd).trim();
                    final String value = params.substring(headerNameEnd + 1).trim();
                    addHeader(name, value);
                }
            } else if (SmtpState.DATA_BODY == response.getNextState()) {
                body.append(params);
            }
        }
    }
