    public static HttpVersion fromVersion(int version)
    {
        switch(version)
        {
            case 9: return HttpVersion.HTTP_0_9;
            case 10: return HttpVersion.HTTP_1_0;
            case 11: return HttpVersion.HTTP_1_1;
            case 20: return HttpVersion.HTTP_2;
            default: throw new IllegalArgumentException();
        }
    }
