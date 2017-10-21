    protected String buildValidatorKey(Class clazz, String context) {
        ActionInvocation invocation = ActionContext.getContext().getActionInvocation();
        ActionProxy proxy = invocation.getProxy();
        ActionConfig config = proxy.getConfig();

        StringBuilder sb = new StringBuilder(clazz.getName());
        sb.append("/");
        if (StringUtils.isNotBlank(config.getPackageName())) {
            sb.append(config.getPackageName());
            sb.append("/");
        }

        // the key needs to use the name of the action from the config file,
        // instead of the url, so wild card actions will have the same validator
        // see WW-2996

        // UPDATE:
        // WW-3753 Using the config name instead of the context only for
        // wild card actions to keep the flexibility provided
        // by the original design (such as mapping different contexts
        // to the same action and method if desired)

        // UPDATE:
        // WW-4536 Using NameVariablePatternMatcher allows defines actions
        // with patterns enclosed with '{}', it's similar case to WW-3753
        String configName = config.getName();
        if (configName.contains(ActionConfig.WILDCARD) || (configName.contains("{") && configName.contains("}"))) {
            sb.append(configName);
            sb.append("|");
            sb.append(proxy.getMethod());
        } else {
            sb.append(context);
        }
        
        return sb.toString();
    }
