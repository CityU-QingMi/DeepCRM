    public void testInsecureParameters() throws Exception {
        // given
        loadConfigurationProviders(new XWorkConfigurationProvider(), new XmlConfigurationProvider("xwork-param-test.xml"));
        final Map<String, Object> params = new HashMap<String, Object>() {
            {
                put("name", "(#context[\"xwork.MethodAccessor.denyMethodExecution\"]= new " +
                        "java.lang.Boolean(false), #_memberAccess[\"allowStaticMethodAccess\"]= new java.lang.Boolean(true), " +
                        "@java.lang.Runtime@getRuntime().exec('mkdir /tmp/PWNAGE'))(meh)");
                put("top['name'](0)", "true");
                put("expression", "#f=#_memberAccess.getClass().getDeclaredField('allowStaticMethodAccess'),#f.setAccessible(true),#f.set(#_memberAccess,true),#req=@org.apache.struts2.ServletActionContext@getRequest(),#resp=@org.apache.struts2.ServletActionContext@getResponse().getWriter(),#resp.println(#req.getRealPath('/')),#resp.close()");
            }
        };

        ParametersInterceptor pi = new ParametersInterceptor();
        container.inject(pi);
        ValueStack vs = ActionContext.getContext().getValueStack();

        // when
        ValidateAction action = new ValidateAction();
        pi.setParameters(action, vs, HttpParameters.create(params).build());

        // then
        assertEquals(3, action.getActionMessages().size());

        String msg1 = action.getActionMessage(0);
        String msg2 = action.getActionMessage(1);
        String msg3 = action.getActionMessage(2);

        assertEquals("Error setting expression 'expression' with value '#f=#_memberAccess.getClass().getDeclaredField('allowStaticMethodAccess'),#f.setAccessible(true),#f.set(#_memberAccess,true),#req=@org.apache.struts2.ServletActionContext@getRequest(),#resp=@org.apache.struts2.ServletActionContext@getResponse().getWriter(),#resp.println(#req.getRealPath('/')),#resp.close()'", msg1);
        assertEquals("Error setting expression 'name' with value '(#context[\"xwork.MethodAccessor.denyMethodExecution\"]= new java.lang.Boolean(false), #_memberAccess[\"allowStaticMethodAccess\"]= new java.lang.Boolean(true), @java.lang.Runtime@getRuntime().exec('mkdir /tmp/PWNAGE'))(meh)'", msg2);
        assertEquals("Error setting expression 'top['name'](0)' with value 'true'", msg3);
        assertNull(action.getName());
    }
