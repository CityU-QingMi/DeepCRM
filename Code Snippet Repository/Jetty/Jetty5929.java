    public void visitSecurityRole(WebAppContext context, Descriptor descriptor, XmlParser.Node node)
    {
        if (context.getSecurityHandler() == null)
        {
            LOG.warn("security-role declared but SecurityHandler==null");
            return;
        }
        //ServletSpec 3.0, p74 elements with multiplicity >1 are additive when merged
        XmlParser.Node roleNode = node.get("role-name");
        String role = roleNode.toString(false, true);
        ((ConstraintAware)context.getSecurityHandler()).addRole(role);
    }
