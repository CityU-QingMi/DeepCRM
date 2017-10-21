    private static int index(HttpVersion version)
    {
        switch (version)
        {
            case HTTP_1_0:
            case HTTP_1_1:
                return 0;

            case HTTP_2:
                return 1;

            default:
                return -1;
        }
    }
