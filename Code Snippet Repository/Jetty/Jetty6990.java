    private String[] split(String str, int partSize)
    {
        int strLength = str.length();
        int count = (int)Math.ceil((double)str.length() / partSize);
        String ret[] = new String[count];
        int idx;
        for (int i = 0; i < count; i++)
        {
            idx = (i * partSize);
            ret[i] = str.substring(idx,Math.min(idx + partSize,strLength));
        }
        return ret;
    }
