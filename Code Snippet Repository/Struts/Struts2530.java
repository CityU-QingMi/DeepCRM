    private ServletContext mockServletContext(String resultPath) {
        ServletContext context = EasyMock.createStrictMock(ServletContext.class);

        // Setup some mock jsps
        Set<String> resources = new HashSet<>();
        resources.add(resultPath + "/namespace/action.jsp");
        resources.add(resultPath + "/namespace/action-success.jsp");
        resources.add(resultPath + "/namespace/action-failure.jsp");
        EasyMock.expect(context.getResourcePaths(resultPath + "/namespace/")).andReturn(resources);
        EasyMock.replay(context);
        return context;
    }
