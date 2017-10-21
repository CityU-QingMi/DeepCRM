    @Inject
    public void setContainer(Container container) {
        super.setContainer(container);
        bindings = new TreeMap<String, Set<Binding>>();
        bindings.put(ObjectFactory.class.getName(), addBindings(container, ObjectFactory.class, StrutsConstants.STRUTS_OBJECTFACTORY));
        bindings.put(XWorkConverter.class.getName(), addBindings(container, XWorkConverter.class, StrutsConstants.STRUTS_XWORKCONVERTER));
        bindings.put(TextProvider.class.getName(), addBindings(container, TextProvider.class, StrutsConstants.STRUTS_XWORKTEXTPROVIDER));
        bindings.put(ActionProxyFactory.class.getName(), addBindings(container, ActionProxyFactory.class, StrutsConstants.STRUTS_ACTIONPROXYFACTORY));
        bindings.put(ObjectTypeDeterminer.class.getName(), addBindings(container, ObjectTypeDeterminer.class, StrutsConstants.STRUTS_OBJECTTYPEDETERMINER));
        bindings.put(ActionMapper.class.getName(), addBindings(container, ActionMapper.class, StrutsConstants.STRUTS_MAPPER_CLASS));
        bindings.put(MultiPartRequest.class.getName(), addBindings(container, MultiPartRequest.class, StrutsConstants.STRUTS_MULTIPART_PARSER));
        bindings.put(FreemarkerManager.class.getName(), addBindings(container, FreemarkerManager.class, StrutsConstants.STRUTS_FREEMARKER_MANAGER_CLASSNAME));
        bindings.put(VelocityManager.class.getName(), addBindings(container, VelocityManager.class, StrutsConstants.STRUTS_VELOCITY_MANAGER_CLASSNAME));
        bindings.put(UrlRenderer.class.getName(), addBindings(container, UrlRenderer.class, StrutsConstants.STRUTS_URL_RENDERER));
    }
