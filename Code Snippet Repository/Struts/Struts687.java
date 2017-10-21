    public Object getAttribute(String key) {
        if (key == null) {
            throw new NullPointerException("You must specify a key value");
        }

        if (disableRequestAttributeValueStackLookup || key.startsWith("javax.servlet")) {
            // don't bother with the standard javax.servlet attributes, we can short-circuit this
            // see WW-953 and the forums post linked in that issue for more info
            return super.getAttribute(key);
        }

        ActionContext ctx = ActionContext.getContext();
        Object attribute = super.getAttribute(key);

        if (ctx != null && attribute == null) {
            boolean alreadyIn = isTrue((Boolean) ctx.get(REQUEST_WRAPPER_GET_ATTRIBUTE));

            // note: we don't let # come through or else a request for
            // #attr.foo or #request.foo could cause an endless loop
            if (!alreadyIn && !key.contains("#")) {
                try {
                    // If not found, then try the ValueStack
                    ctx.put(REQUEST_WRAPPER_GET_ATTRIBUTE, Boolean.TRUE);
                    ValueStack stack = ctx.getValueStack();
                    if (stack != null) {
                        attribute = stack.findValue(key);
                    }
                } finally {
                    ctx.put(REQUEST_WRAPPER_GET_ATTRIBUTE, Boolean.FALSE);
                }
            }
        }
        return attribute;
    }
