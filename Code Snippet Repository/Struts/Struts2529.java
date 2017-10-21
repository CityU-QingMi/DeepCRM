    private PackageConfig createPackageConfigBuilder(String namespace) {
        ResultTypeConfig resultType = new ResultTypeConfig.Builder("dispatcher", "org.apache.struts2.result.ServletDispatcherResult").
                addParam("key", "value").addParam("key1", "value1").defaultResultParam("location").build();

        ResultTypeConfig redirect = new ResultTypeConfig.Builder("redirectAction",
                "org.apache.struts2.result.ServletActionRedirectResult").defaultResultParam("actionName").build();

        ResultTypeConfig ftlResultType = new ResultTypeConfig.Builder("freemarker",
                "org.apache.struts2.views.freemarker.FreemarkerResult").defaultResultParam("location").build();

        return new PackageConfig.Builder("package").
                namespace(namespace).
                defaultResultType("dispatcher").
                addResultTypeConfig(resultType).
                addResultTypeConfig(redirect).
                addResultTypeConfig(ftlResultType).build();
    }
