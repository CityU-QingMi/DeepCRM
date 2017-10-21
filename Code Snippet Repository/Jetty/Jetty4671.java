    @Parameters(name = "")
    public static List<Object[]> getCases() throws Exception
    {
        File usecases = MavenTestingUtils.getTestResourceDir("usecases/");
        File[] cases = usecases.listFiles((dir, name) -> name.endsWith(".assert.txt"));
        Arrays.sort(cases);
        
        List<Object[]> ret = new ArrayList<>();
        for (File assertTxt : cases)
        {
            String caseName = assertTxt.getName().replace(".assert.txt", "");
            ret.add(new Object[]{caseName});
        }
        
        return ret;
    }
