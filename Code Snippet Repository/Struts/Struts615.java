    private String buildToken(String name) {
        Map context = stack.getContext();
        Object myToken = context.get(name);

        if (myToken == null) {
            myToken = TokenHelper.setToken(name);
            context.put(name, myToken);
        }

        return myToken.toString();
    }
