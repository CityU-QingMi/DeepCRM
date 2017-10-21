    @Parameters(name = "")
    public static Collection<Object[]> data()
    {
        List<Object[]> data = new ArrayList<>();

        final String OPENCONTENT = "this is open content";

        data.add(new Object[] { "/ctx/all/index.txt", HttpStatus.OK_200, OPENCONTENT });
        data.add(new Object[] { "/ctx/ALL/index.txt", HttpStatus.NOT_FOUND_404, null });
        data.add(new Object[] { "/ctx/ALL/Fred/../index.txt", HttpStatus.NOT_FOUND_404, null });
        data.add(new Object[] { "/ctx/../bar/../ctx/all/index.txt", HttpStatus.OK_200, OPENCONTENT });
        data.add(new Object[] { "/ctx/forbid/index.txt", HttpStatus.FORBIDDEN_403, null });
        data.add(new Object[] { "/ctx/all/../forbid/index.txt", HttpStatus.FORBIDDEN_403, null });
        data.add(new Object[] { "/ctx/FoRbId/index.txt", HttpStatus.NOT_FOUND_404, null });

        return data;
    }
