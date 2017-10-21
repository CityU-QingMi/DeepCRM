    public StandardDescriptorProcessor ()
    {
        try
        {
            registerVisitor("context-param", this.getClass().getMethod("visitContextParam", __signature));
            registerVisitor("display-name", this.getClass().getMethod("visitDisplayName", __signature));
            registerVisitor("servlet", this.getClass().getMethod("visitServlet",  __signature));
            registerVisitor("servlet-mapping", this.getClass().getMethod("visitServletMapping",  __signature));
            registerVisitor("session-config", this.getClass().getMethod("visitSessionConfig",  __signature));
            registerVisitor("mime-mapping", this.getClass().getMethod("visitMimeMapping",  __signature));
            registerVisitor("welcome-file-list", this.getClass().getMethod("visitWelcomeFileList",  __signature));
            registerVisitor("locale-encoding-mapping-list", this.getClass().getMethod("visitLocaleEncodingList",  __signature));
            registerVisitor("error-page", this.getClass().getMethod("visitErrorPage",  __signature));
            registerVisitor("taglib", this.getClass().getMethod("visitTagLib",  __signature));
            registerVisitor("jsp-config", this.getClass().getMethod("visitJspConfig",  __signature));
            registerVisitor("security-constraint", this.getClass().getMethod("visitSecurityConstraint",  __signature));
            registerVisitor("login-config", this.getClass().getMethod("visitLoginConfig",  __signature));
            registerVisitor("security-role", this.getClass().getMethod("visitSecurityRole",  __signature));
            registerVisitor("filter", this.getClass().getMethod("visitFilter",  __signature));
            registerVisitor("filter-mapping", this.getClass().getMethod("visitFilterMapping",  __signature));
            registerVisitor("listener", this.getClass().getMethod("visitListener",  __signature));
            registerVisitor("distributable", this.getClass().getMethod("visitDistributable",  __signature));
            registerVisitor("deny-uncovered-http-methods", this.getClass().getMethod("visitDenyUncoveredHttpMethods", __signature));
        }
        catch (Exception e)
        {
            throw new IllegalStateException(e);
        }
    }
