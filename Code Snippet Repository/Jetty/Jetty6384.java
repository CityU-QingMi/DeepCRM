    public static boolean isTransmittable(int statusCode)
    {
        return (statusCode == NORMAL) ||
                (statusCode == SHUTDOWN) ||
                (statusCode == PROTOCOL) ||
                (statusCode == BAD_DATA) ||
                (statusCode == BAD_PAYLOAD) ||
                (statusCode == POLICY_VIOLATION) ||
                (statusCode == MESSAGE_TOO_LARGE) ||
                (statusCode == REQUIRED_EXTENSION) ||
                (statusCode == SERVER_ERROR) ||
                (statusCode == SERVICE_RESTART) ||
                (statusCode == TRY_AGAIN_LATER) ||
                (statusCode == INVALID_UPSTREAM_RESPONSE);
    }
