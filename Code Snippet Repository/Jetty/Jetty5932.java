    public void visitDenyUncoveredHttpMethods(WebAppContext context, Descriptor descriptor, XmlParser.Node node)
    {
        if (context.getSecurityHandler() == null)
        {
            LOG.warn("deny-uncovered-http-methods declared but SecurityHandler==null");
            return;
        }

        ((ConstraintAware)context.getSecurityHandler()).setDenyUncoveredHttpMethods(true);
    }
