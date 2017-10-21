    private void assertValidStatusCode(int statusCode)
    {
        // Status Codes outside of RFC6455 defined scope
        if ((statusCode <= 999) || (statusCode >= 5000))
        {
            throw new ProtocolException("Out of range close status code: " + statusCode);
        }

        // Status Codes not allowed to exist in a Close frame (per RFC6455)
        if ((statusCode == StatusCode.NO_CLOSE) || (statusCode == StatusCode.NO_CODE) || (statusCode == StatusCode.FAILED_TLS_HANDSHAKE))
        {
            throw new ProtocolException("Frame forbidden close status code: " + statusCode);
        }

        // Status Code is in defined "reserved space" and is declared (all others are invalid)
        if ((statusCode >= 1000) && (statusCode <= 2999) && !StatusCode.isTransmittable(statusCode))
        {
            throw new ProtocolException("RFC6455 and IANA Undefined close status code: " + statusCode);
        }
    }
