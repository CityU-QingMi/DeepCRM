    public SimpleTheme() {
        setHandlerFactories(new HashMap<String, List<TagHandlerFactory>>() {
            {
                put("text", new FactoryList(TextFieldHandler.class, ScriptingEventsHandler.class, CommonAttributesHandler.class, DynamicAttributesHandler.class));
                put("textfield", new FactoryList(TextFieldHandler.class, ScriptingEventsHandler.class, CommonAttributesHandler.class, DynamicAttributesHandler.class));
                put("datetextfield", new FactoryList(DateTextFieldHandler.class, ScriptingEventsHandler.class, CommonAttributesHandler.class));
                put("select", new FactoryList(SelectHandler.class, ScriptingEventsHandler.class, CommonAttributesHandler.class, DynamicAttributesHandler.class));
                put("form", new FactoryList(FormHandler.class, ScriptingEventsHandler.class, CommonAttributesHandler.class, DynamicAttributesHandler.class));
                put("form-close", new FactoryList(FormHandler.CloseHandler.class));
                put("a", new FactoryList(AnchorHandler.class));
                put("a-close", new FactoryList(AnchorHandler.CloseHandler.class, ScriptingEventsHandler.class, CommonAttributesHandler.class, DynamicAttributesHandler.class));
                put("checkbox", new FactoryList(CheckboxHandler.class, ScriptingEventsHandler.class, CommonAttributesHandler.class, DynamicAttributesHandler.class));
                put("file", new FactoryList(FileHandler.class, ScriptingEventsHandler.class, CommonAttributesHandler.class, DynamicAttributesHandler.class));
                put("password", new FactoryList(PasswordHandler.class, ScriptingEventsHandler.class, CommonAttributesHandler.class, DynamicAttributesHandler.class));
                put("label", new FactoryList(LabelHandler.class, ScriptingEventsHandler.class, CommonAttributesHandler.class, DynamicAttributesHandler.class));
                put("reset", new FactoryList(ResetHandler.class, ScriptingEventsHandler.class, CommonAttributesHandler.class, DynamicAttributesHandler.class));
                put("submit", new FactoryList(SubmitHandler.class, ScriptingEventsHandler.class, CommonAttributesHandler.class, DynamicAttributesHandler.class));
                put("submit-close", new FactoryList(SubmitHandler.CloseHandler.class));
                put("textarea", new FactoryList(TextAreaHandler.class, ScriptingEventsHandler.class, CommonAttributesHandler.class, DynamicAttributesHandler.class));
                put("radiomap", new FactoryList(RadioHandler.class, ScriptingEventsHandler.class, CommonAttributesHandler.class, DynamicAttributesHandler.class));
                put("checkboxlist", new FactoryList(CheckboxListHandler.class, ScriptingEventsHandler.class, CommonAttributesHandler.class, DynamicAttributesHandler.class));
                put("actionerror", new FactoryList(ActionErrorHandler.class));
                put("token", new FactoryList(TokenHandler.class));
                put("actionmessage", new FactoryList(ActionMessageHandler.class));
                put("head", new FactoryList(HeadHandler.class));
                put("hidden", new FactoryList(HiddenHandler.class));
                put("fielderror", new FactoryList(FieldErrorHandler.class));
                put("empty", new FactoryList(EmptyHandler.class));
           }
        });
        setName("simple");
    }
